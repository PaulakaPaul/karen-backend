package com.karen.drone.util;

import com.karen.drone.comment.model.Comment;
import com.karen.drone.event.model.Event;
import com.karen.drone.event.model.components.Coords;
import com.karen.drone.comment.model.persistence.CommentDAO;
import com.karen.drone.event.model.persistence.EventDAO;
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

    public static Event eventFromDAO(EventDAO dao, List<Comment> comments) {
        Coords coords = new Coords(dao.getLongitude(), dao.getLongitude());
        Event event = new Event();
        event.setEventId(dao.getEventId());
        event.setDroneId(dao.getDroneId());
        event.setCoords(coords);
        event.setImage(Base64.getEncoder().encodeToString(dao.getImage()));
        event.setStatus(dao.getStatus());
        event.setComments(comments);
        event.setReportedAt(dao.getReportedAt());
        return event;
    }
}
