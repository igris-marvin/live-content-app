package igris.marvin.server_socket.api.services.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import jakarta.annotation.PostConstruct;

@Component
public class StudentListener {

    private final SimpMessagingTemplate mt;

    @Autowired
    private FirebaseDatabase firedb;

    private final String path = "student";

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public StudentListener(
        SimpMessagingTemplate mt
    ) {

        this.mt = mt;
        
    }

    @PostConstruct
    public void studentListener() {
        
        try {

            firedb
                .getReference()
                .child(path)
                .addChildEventListener(
                        new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                // TODO Auto-generated method stub
                                throw new UnsupportedOperationException("Unimplemented method 'onChildAdded'");
                            }

                            @Override
                            public void onChildChanged(
                                DataSnapshot snapshot,
                                String previousChildName
                            ) {
                                String userId = snapshot.getKey();
                                String updatedData = snapshot.getValue().toString();
                                
                                System.out.println("/n/n[ UserId: "+userId+"\nUpdated Data: "+updatedData+"]/n/n");
                                
                                mt.convertAndSend(
                                    "/queue/updates/" + userId,
                                    updatedData
                                );

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot snapshot) {
                                // TODO Auto-generated method stub
                                throw new UnsupportedOperationException("Unimplemented method 'onChildRemoved'");
                            }

                            @Override
                            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                                // TODO Auto-generated method stub
                                throw new UnsupportedOperationException("Unimplemented method 'onChildMoved'");
                            }

                            @Override
                            public void onCancelled(
                                    DatabaseError e) {
                                logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: "
                                        + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                                e.toException().printStackTrace();
                            }
                        });

        } catch (Exception e) {
            logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage()
                    + " || Origin Class: " + this.getClass().getName() + " ] ");
            e.printStackTrace();
        }
    }
}
