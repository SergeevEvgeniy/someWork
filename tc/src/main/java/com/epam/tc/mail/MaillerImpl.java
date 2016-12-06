package com.epam.tc.mail;

import com.epam.tc.model.Course;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MaillerImpl implements Mailler {

    private static final Logger LOG = LoggerFactory.getLogger(MaillerImpl.class);

    private static final String COURSE_ANNOUNCEMENT_SUBJECT = "Course Announcement";
    private static final String MANAGER_EMAIL = "unknownred1225@gmail.com";

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailContentBuilder contentBuilder;

    @Override
    public void sendMail(Course course) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject(COURSE_ANNOUNCEMENT_SUBJECT);
            helper.setTo(MANAGER_EMAIL);
            helper.setCc(course.getOwner().getEmail());
            helper.setText(contentBuilder.build(course), true);
        } catch (MessagingException mex) {
            LOG.error("Error sending mail ", mex);
        }
        javaMailSender.send(message);
    }
}
