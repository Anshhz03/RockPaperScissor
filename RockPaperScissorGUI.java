//frontend
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RockPaperScissorGUI extends JFrame implements ActionListener{
    //player buttons
    JButton rockButton, paperButton, scissorButton;

    //will display the choice of the computer
    JLabel computerChoice;

    JLabel computerScoreLabel, playerScoreLabel;

    //backend obj
    RockPaperScissor rockPaperScissor;
    public RockPaperScissorGUI(){
        //invoke jFrame constructor and add title to the GUI
        super("Rock Paper Scissor");

        //setting the size of the GUI
        setSize(500, 650);

        //set layout to null to disable layout management, so we can use absolute positioning for the elements
        setLayout(null);

        //load GUI in the center every time we run the application
        setLocationRelativeTo(null);

        //initialize the backend obj
        rockPaperScissor =new RockPaperScissor();

        //add GUI components
        addGuiComponents();

    }

    private void addGuiComponents(){

        //creating computer score label
        computerScoreLabel=new JLabel("Computer: 0");
        computerScoreLabel.setBounds(0,40,450,30);
        computerScoreLabel.setFont(new Font("Dialog", Font.BOLD,26));
        computerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(computerScoreLabel);

        //create computer choice
        computerChoice=new JLabel("?");
        computerChoice.setBounds(170,187,95,80);
        computerChoice.setFont(new Font("Dialog", Font.PLAIN,18));
        computerChoice.setHorizontalAlignment(SwingConstants.CENTER);
        computerChoice.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(computerChoice);

        //create player score label
        playerScoreLabel=new JLabel("Player: 0");
        playerScoreLabel.setBounds(0,300,90,35);
        playerScoreLabel.setFont(new Font("Dialog", Font.BOLD,15));
        playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(playerScoreLabel);

        //player buttons

        //rock Button
        rockButton=new JButton("Rock");
        rockButton.setBounds(40,350,95,81);
        rockButton.setFont(new Font("Dialog",Font.PLAIN,18));
        rockButton.addActionListener(this);

        add(rockButton);

        //paper Button
        paperButton=new JButton("Paper");
        paperButton.setBounds(190,350,95,81);
        paperButton.setFont(new Font("Dialog", Font.PLAIN,18));
        paperButton.addActionListener(this);

        add(paperButton);

        //scissor Button
        scissorButton=new JButton("Scissor");
        scissorButton.setBounds(350,350,95,81);
        scissorButton.setFont(new Font("Dialog", Font.PLAIN,18));
        scissorButton.addActionListener(this);

        add(scissorButton);

//        showDialog("Text Message");
    }

    //displays a message dialog which will show the winner and a try again button
    private void showDialog(String message){
        JDialog resultDialog=new JDialog(this,"Result",true);
        resultDialog.setSize(230,130);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        //message label
        JLabel resultLabel=new JLabel(message);
        resultLabel.setFont(new Font("Dialog",Font.BOLD,18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultDialog.add(resultLabel, BorderLayout.CENTER);

        //try again button
        JButton tryAgainButton=new JButton("Try Again");
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset computer choice
                computerChoice.setText("?");

                //close the dialog box
                resultDialog.dispose();
            }
        });
        resultDialog.add(tryAgainButton, BorderLayout.SOUTH);

        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //get player choice
        String playerChoice=e.getActionCommand().toString();

        //play rock paper scissor and store result into string var
        String result=rockPaperScissor.playRockPaperScissor(playerChoice);

        //load computer's choice
        computerChoice.setText(rockPaperScissor.getComputerChoice());

        //update score
        computerScoreLabel.setText("Computer:" + rockPaperScissor.getComputerScore());
        playerScoreLabel.setText("Player:" +rockPaperScissor.getPlayerScore());

        //display resultDialog
        showDialog(result);


    }
}
