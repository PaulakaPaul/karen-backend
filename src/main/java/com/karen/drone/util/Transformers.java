package com.karen.drone.util;

import com.karen.drone.comment.model.Comment;
import com.karen.drone.event.model.Event;
import com.karen.drone.event.model.components.Coords;
import com.karen.drone.comment.model.persistence.CommentDAO;
import com.karen.drone.event.model.persistence.EventDAO;
import com.karen.drone.submission.model.Submission;
import com.karen.drone.submission.model.persistence.SubmissionDAO;
import com.karen.drone.user.models.UserProfile;
import com.karen.drone.user.models.persistence.UserDAO;

import java.util.Base64;
import java.util.List;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class Transformers {

    public static UserProfile profileFromDAO(UserDAO dao) {
        UserProfile profile = new UserProfile();
        profile.setUserId(dao.getUserId());
        profile.setName(dao.getName());
        return profile;
    }

    public static Comment commentFromDAO(CommentDAO dao, UserProfile profile) {
        Comment comm = new Comment();
        comm.setCommentId(dao.getCommentId());
        comm.setMessage(dao.getMessage());
        comm.setPostedAt(dao.getPostedAt());
        comm.setPostedBy(profile);
        return comm;
    }

    public static Submission submissionFromDAO(SubmissionDAO dao, UserProfile profile) {
        Submission subb = new Submission();
        subb.setSubmissionId(dao.getSubmissionId());
        subb.setMessage(dao.getMessage());
        subb.setImage(ImageCodec.encodeImage(dao.getImageType(), dao.getImage()));
        subb.setStatus(dao.getStatus());
        subb.setSubmittedAt(dao.getSubmittedAt());
        subb.setSubmittedBy(profile);
        return subb;
    }

    public static Event eventFromDAO(EventDAO dao, List<Comment> comments, List<Submission> submissions) {
        Coords coords = new Coords(dao.getLongitude(), dao.getLatitude());
        Event event = new Event();
        event.setEventId(dao.getEventId());
        event.setDroneId(dao.getDroneId());
        event.setCoords(coords);
        event.setImage(ImageCodec.encodeImage(dao.getImageType(), dao.getImage()));
        event.setStatus(dao.getStatus());
        event.setComments(comments);
        event.setSubmissions(submissions);
        event.setReportedAt(dao.getReportedAt());
        return event;
    }
}
