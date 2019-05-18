package com.karen.drone.video;

import com.karen.drone.video.model.Video;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
@Controller
public class VideoController {

    @MessageMapping("/video")
    @SendTo("/frame")
    public Video send(@Payload Video video) {
        System.out.println(video.getVideo());
        return video;
    }
}
