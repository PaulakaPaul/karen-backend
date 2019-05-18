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
import com.karen.drone.exceptions.NotFoundException;
import com.karen.drone.security.AuthCtx;
import com.karen.drone.user.UserRepository;
import com.karen.drone.user.models.UserProfile;
import com.karen.drone.user.models.components.UserRole;
import com.karen.drone.user.models.persistence.UserDAO;
import com.karen.drone.util.Image;
import com.karen.drone.util.ImageCodec;
import com.karen.drone.util.Transformers;
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
            System.out.println("Image can not be decoded");
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
    public void deleteCommentFromEvent(@PathVariable Integer commentId) {
        CommentDAO commentDAO = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment with id '" + commentId + "' was not found")
        );

        AuthCtx authCtx = (AuthCtx) SecurityContextHolder.getContext().getAuthentication();
        if(authCtx.getRole() != UserRole.ADMIN && commentDAO.getPostedBy().getUserId() != authCtx.getId()) {
            throw new ForbiddenFailure("Not allowed to delete this comment");
        }

        commentRepository.delete(commentDAO);
    }

    private Event getEventFromDAO(EventDAO dao) {
        List<Comment> comments = new ArrayList<>();
        dao.getComments().forEach(commDao -> {
            UserProfile profile = Transformers.profileFromDAO(commDao.getPostedBy());
            Comment comm = Transformers.commentFromDAO(commDao, profile);
            comments.add(comm);
        });
        return Transformers.eventFromDAO(dao, comments);
    }

}
