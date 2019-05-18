package com.karen.drone.event;

import com.karen.drone.comment.CommentRepository;
import com.karen.drone.comment.model.Comment;
import com.karen.drone.comment.model.CommentDefinition;
import com.karen.drone.comment.model.CommentDefinitionResponse;
import com.karen.drone.event.model.*;
import com.karen.drone.event.model.components.EventStatus;
import com.karen.drone.comment.model.persistence.CommentDAO;
import com.karen.drone.event.model.persistence.EventDAO;
import com.karen.drone.exceptions.AuthenticationException;
import com.karen.drone.exceptions.ForbiddenFailure;
import com.karen.drone.exceptions.InvalidInputException;
import com.karen.drone.exceptions.NotFoundException;
import com.karen.drone.security.AuthCtx;
import com.karen.drone.submission.SubmissionRepository;
import com.karen.drone.submission.model.Submission;
import com.karen.drone.submission.model.SubmissionDefinition;
import com.karen.drone.submission.model.SubmissionDefinitionResponse;
import com.karen.drone.submission.model.components.SubmissionStatus;
import com.karen.drone.submission.model.persistence.SubmissionDAO;
import com.karen.drone.user.UserRepository;
import com.karen.drone.user.models.UserProfile;
import com.karen.drone.user.models.components.UserRole;
import com.karen.drone.user.models.persistence.UserDAO;
import com.karen.drone.util.Image;
import com.karen.drone.util.ImageCodec;
import com.karen.drone.util.Transformers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @GetMapping("/event")
    public List<Event> getAllEvents() {
        List<EventDAO> eventDaos =  eventRepository.findAll();
        List<Event> events = new ArrayList<>();
        eventDaos.forEach(eventDao -> {
            Event event = getEventFromDAO(eventDao);
            events.add(event);
        });
        return events;
    }

    @ApiOperation(value = "Create event")
    @PostMapping("/event")
    public ResponseEntity<EventDefinitionResponse> createEvent(@RequestBody EventDefinition def) {
        EventDAO dao = new EventDAO();
        dao.setEventId(UUID.randomUUID());
        dao.setDroneId(def.getDroneId());

        Image img = ImageCodec.decodeImage(def.getImage());
        if(img != null) {
            dao.setImage(img.getBytes());
            dao.setImageType(img.getType());
        }
        else {
            System.out.println("EVENT: Image can not be decoded");
        }

        dao.setLongitude(def.getCoords().getLongitude());
        dao.setLatitude(def.getCoords().getLatitude());
        dao.setStatus(EventStatus.OPENED);
        dao.setReportedAt(new Date());

        eventRepository.save(dao);

        EventDefinitionResponse resp = new EventDefinitionResponse(dao.getEventId());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get event by id")
    @GetMapping("/event/{eventId}")
    public Event getEventById(@PathVariable UUID eventId) {
        EventDAO eventDao = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Event with id '" + eventId + "' was not found")
        );
        return getEventFromDAO(eventDao);
    }

    @ApiOperation(value = "Delete event by id - Only admins")
    @DeleteMapping("/event/{eventId}")
    public void deleteEventById(@PathVariable UUID eventId) {
        AuthCtx ctx = (AuthCtx) SecurityContextHolder.getContext().getAuthentication();
        if(ctx == null || ctx.getRole() != UserRole.ADMIN) {
            throw new ForbiddenFailure("Only admins are allowed to delete events");
        }
        EventDAO eventDao = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Event with id '" + eventId + "' was not found")
        );
        eventRepository.delete(eventDao);
    }

    @ApiOperation(value = "Post comment")
    @PostMapping("/event/{eventId}/comment")
    public ResponseEntity<CommentDefinitionResponse> postComment(@PathVariable UUID eventId, @RequestBody CommentDefinition def) {
        EventDAO eventDao = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Event with id '" + eventId + "' was not found")
        );
        AuthCtx authCtx = (AuthCtx) SecurityContextHolder.getContext().getAuthentication();
        UserDAO userDao = userRepository.findById(authCtx.getId()).orElseThrow(
                () -> new AuthenticationException("Failed to find user associated with this token")
        );

        CommentDAO dao = new CommentDAO();
        dao.setCommentId(UUID.randomUUID());
        dao.setEvent(eventDao);
        dao.setMessage(def.getMessage());
        dao.setPostedBy(userDao);
        dao.setPostedAt(new Date());

        commentRepository.save(dao);

        CommentDefinitionResponse resp = new CommentDefinitionResponse(dao.getCommentId());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete comment")
    @DeleteMapping("/comment/{commentId}")
    public void deleteCommentFromEvent(@PathVariable UUID commentId) {
        CommentDAO commentDAO = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment with id '" + commentId + "' was not found")
        );

        AuthCtx authCtx = (AuthCtx) SecurityContextHolder.getContext().getAuthentication();
        if(authCtx.getRole() != UserRole.ADMIN && commentDAO.getPostedBy().getUserId() != authCtx.getId()) {
            throw new ForbiddenFailure("Not allowed to delete this comment");
        }

        commentRepository.delete(commentDAO);
    }

    @ApiOperation(value = "Post submission")
    @PostMapping("/event/{eventId}/submission")
    public ResponseEntity<SubmissionDefinitionResponse> postSubmission(@PathVariable UUID eventId, @RequestBody SubmissionDefinition def) {
        EventDAO eventDao = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Event with id '" + eventId + "' was not found")
        );
        AuthCtx authCtx = (AuthCtx) SecurityContextHolder.getContext().getAuthentication();
        UserDAO userDao = userRepository.findById(authCtx.getId()).orElseThrow(
                () -> new AuthenticationException("Failed to find user associated with this token")
        );
        if(eventDao.getStatus() == EventStatus.CLOSED) {
            throw new ForbiddenFailure("Not allowed to submit to a closed event");
        }

        SubmissionDAO dao = new SubmissionDAO();
        dao.setSubmissionId(UUID.randomUUID());
        dao.setMessage(def.getMessage());

        Image img = ImageCodec.decodeImage(def.getImage());
        if(img != null) {
            dao.setImage(img.getBytes());
            dao.setImageType(img.getType());
        }
        else {
            System.out.println("SUBMISSION: Image can not be decoded");
        }
        dao.setEvent(eventDao);
        dao.setSubmittedBy(userDao);
        dao.setSubmittedAt(new Date());
        dao.setStatus(SubmissionStatus.PENDING);

        submissionRepository.save(dao);

        SubmissionDefinitionResponse resp = new SubmissionDefinitionResponse(dao.getSubmissionId());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Approve/Decline a submission - Admin Only")
    @PutMapping("/submission/{submissionId}")
    public void changeSubmissionStatus(@PathVariable UUID submissionId, @RequestParam(name = "status") SubmissionStatus newStatus) {
        SubmissionDAO submissionDAO = submissionRepository.findById(submissionId).orElseThrow(
                () -> new NotFoundException("Submission with id '" + submissionId + "' was not found")
        );

        EventDAO eventDao = submissionDAO.getEvent();
        if(eventDao.getStatus() == EventStatus.CLOSED) {
            throw new ForbiddenFailure("Not allowed to modify a closed event");
        }

        AuthCtx authCtx = (AuthCtx) SecurityContextHolder.getContext().getAuthentication();
        if(authCtx.getRole() != UserRole.ADMIN) {
            throw new ForbiddenFailure("Only admins are allowed to change submission status");
        }

        if(submissionDAO.getStatus() != SubmissionStatus.PENDING) {
            throw new ForbiddenFailure("Can only change status of a pending submission");
        }

        // close event
        if(newStatus == SubmissionStatus.ACCEPTED) {
            eventDao.setStatus(EventStatus.CLOSED);
            eventRepository.save(eventDao);
        }

        submissionDAO.setStatus(newStatus);
        submissionRepository.save(submissionDAO);
    }

    @ApiOperation(value = "Delete submission")
    @DeleteMapping("/submission/{submissionId}")
    public void deleteSubmissionFromEvent(@PathVariable UUID submissionId) {
        SubmissionDAO submissionDAO = submissionRepository.findById(submissionId).orElseThrow(
                () -> new NotFoundException("Submission with id '" + submissionId + "' was not found")
        );

        EventDAO event = submissionDAO.getEvent();
        if(event.getStatus() == EventStatus.CLOSED) {
            throw new ForbiddenFailure("Not allowed to modify a closed event");
        }

        AuthCtx authCtx = (AuthCtx) SecurityContextHolder.getContext().getAuthentication();
        if(authCtx.getRole() != UserRole.ADMIN && submissionDAO.getSubmittedBy().getUserId() != authCtx.getId()) {
            throw new ForbiddenFailure("Not allowed to delete this submission");
        }

        if(submissionDAO.getStatus() == SubmissionStatus.ACCEPTED) {
            throw new ForbiddenFailure("Not allowed to delete an accepted submission");
        }

        submissionRepository.delete(submissionDAO);
    }

    private Event getEventFromDAO(EventDAO dao) {
        List<Comment> comments = new ArrayList<>();
        dao.getComments().forEach(commDao -> {
            UserProfile profile = Transformers.profileFromDAO(commDao.getPostedBy());
            Comment comm = Transformers.commentFromDAO(commDao, profile);
            comments.add(comm);
        });
        List<Submission> submissions = new ArrayList<>();
        dao.getSubmissions().forEach(subbDao -> {
            UserProfile profile = Transformers.profileFromDAO(subbDao.getSubmittedBy());
            Submission subb = Transformers.submissionFromDAO(subbDao, profile);
            submissions.add(subb);
        });
        return Transformers.eventFromDAO(dao, comments, submissions);
    }

}
