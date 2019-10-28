package view;

import enums.GameCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Card;
import model.Message;
import model.Player;
import model.User;
import service.RequestService;

/**
 *
 * @author Nemo
 */
public class GameView extends javax.swing.JFrame {

    private RequestService service;
    private List<User> users;
    private List<Player> players;
    private List<Message> messages = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();
    
    private DefaultListModel usersListModel = new DefaultListModel();
    private DefaultListModel playersListModel = new DefaultListModel();
    private DefaultListModel messagesListModel = new DefaultListModel();
    
    private final int THREADS_INTERVAL = 6000;
    
    public GameView(RequestService service) {
        initComponents();
        cardsTextField.setEditable(false);
        this.setLocationRelativeTo(null);
        
        this.service = service;        
        
        getUsersThread.start();
        getPlayersThread.start();
        getMessagesThread.start();
    }

    public RequestService getService() {
        return service;
    }
    
    private final Thread getUsersThread = new Thread() {
        @Override
        public void run() {
            while (true) {

                users = service.getUsers();

                usersListModel.clear();
                users.forEach(user -> {
                    usersListModel.addElement(user);
                });
                usersList.setModel(usersListModel);
                
                try {
                    Thread.sleep(THREADS_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    
    private final Thread getPlayersThread = new Thread() {
        @Override
        public void run() {
            while (true) {
                updatePlayersList();
                
                try {
                    Thread.sleep(THREADS_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    
    private void updatePlayersList() {
        players = service.getPlayers();

        playersListModel.clear();
        players.forEach(user -> {
            playersListModel.addElement(user);
        });
        playersList.setModel(playersListModel);
    }
    
    private final Thread getMessagesThread = new Thread() {
        @Override
        public void run() {
            while (true) {
                Message message = getService().getMessage();
                
                if(!Objects.isNull(message)) {
                    messages.add(message);
                    messagesListModel.clear();
                    messages.forEach(msg -> {
                        messagesListModel.addElement(msg);
                    });
                    chatList.setModel(messagesListModel);
                }
                
                try {
                    Thread.sleep(THREADS_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    
    private void cleanCards() {
        cards.clear();
        cardsTextField.setText("");
        totalScoreLabel.setText("0");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        playersList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        cardsTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totalScoreLabel = new javax.swing.JLabel();
        getCardButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        chatList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        toMessageTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        messageContentTextArea = new javax.swing.JTextArea();
        sendMessageButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        enterGameButton = new javax.swing.JButton();
        stopGameButton = new javax.swing.JButton();
        quitGameButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Listas"));

        jLabel1.setText("Usuários:");

        jScrollPane1.setViewportView(usersList);

        jLabel2.setText("Jogadores:");

        jScrollPane2.setViewportView(playersList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(183, 183, 183))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Partida"));

        jLabel5.setText("Cartas:");

        jLabel6.setText("Total: ");

        totalScoreLabel.setText("0");

        getCardButton.setText("Pedir carta");
        getCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getCardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardsTextField)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totalScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(getCardButton))
                                .addGap(0, 246, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(totalScoreLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(getCardButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jScrollPane3.setViewportView(chatList);

        jLabel3.setText("Para (userId): ");

        messageContentTextArea.setColumns(20);
        messageContentTextArea.setRows(2);
        jScrollPane4.setViewportView(messageContentTextArea);

        sendMessageButton.setText("Enviar");
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Mensagem:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toMessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendMessageButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendMessageButton)
                    .addComponent(jLabel3)
                    .addComponent(toMessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        enterGameButton.setText("Entrar");
        enterGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterGameButtonActionPerformed(evt);
            }
        });

        stopGameButton.setText("Parar");
        stopGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopGameButtonActionPerformed(evt);
            }
        });

        quitGameButton.setText("Sair");
        quitGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitGameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterGameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopGameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitGameButton)))
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enterGameButton)
                            .addComponent(stopGameButton)
                            .addComponent(quitGameButton))
                        .addGap(16, 16, 16)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed
        String userIdTo = toMessageTextField.getText();
        String content = messageContentTextArea.getText();
        
        if("".equals(userIdTo) || "".equals(content)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de enviar a mensagem.");
            return;
        }
        
        if(userIdTo.equals(getService().getUserId())) {
            JOptionPane.showMessageDialog(null, "Não é possível enviar uma mensagem para você mesmo.");
            return;
        }
        
        getService().sendMessage(content, userIdTo);
        
        JOptionPane.showMessageDialog(null, "Mensagem enviada para " + userIdTo + ".");
        toMessageTextField.setText("");
        messageContentTextArea.setText("");
    }//GEN-LAST:event_sendMessageButtonActionPerformed
    
    private void enterGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterGameButtonActionPerformed
        this.service.sendGameCommand(GameCommand.ENTER);
        cleanCards();
        updatePlayersList();
    }//GEN-LAST:event_enterGameButtonActionPerformed

    private void stopGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopGameButtonActionPerformed
        this.service.sendGameCommand(GameCommand.STOP);
        cleanCards();
    }//GEN-LAST:event_stopGameButtonActionPerformed

    private void quitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitGameButtonActionPerformed
        this.service.sendGameCommand(GameCommand.QUIT);
        cleanCards();
        updatePlayersList();
    }//GEN-LAST:event_quitGameButtonActionPerformed
    
    private void getCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getCardButtonActionPerformed
        Card card = this.service.getCard();
        
        if(!Objects.isNull(card)) {
            if(cards.size() > 0)
                cardsTextField.setText(cardsTextField.getText() + ", ");
            
            int score = Integer.parseInt(totalScoreLabel.getText()) + card.number;
            totalScoreLabel.setText(String.valueOf(score));
            cardsTextField.setText(cardsTextField.getText() + card);
            cards.add(card);
        }
        
    }//GEN-LAST:event_getCardButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cardsTextField;
    private javax.swing.JList<String> chatList;
    private javax.swing.JButton enterGameButton;
    private javax.swing.JButton getCardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea messageContentTextArea;
    private javax.swing.JList<String> playersList;
    private javax.swing.JButton quitGameButton;
    private javax.swing.JButton sendMessageButton;
    private javax.swing.JButton stopGameButton;
    private javax.swing.JTextField toMessageTextField;
    private javax.swing.JLabel totalScoreLabel;
    private javax.swing.JList<String> usersList;
    // End of variables declaration//GEN-END:variables
}
