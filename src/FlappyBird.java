import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int frameWidth = 360;
    int frameHeight = 640;

    boolean gameOver;
    int score;

    JLabel scoreLabel;
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // Player Attributes
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY= frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    // Pipes Attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;
    Timer gameLoop;

    Timer pipesCooldown;
    int gravity = 1;
    public FlappyBird(){

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(20, 20, 150, 30);
        add(scoreLabel);

        gameOver = false;
        score = 0;

        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
    //  setBackground(Color.blue);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bintang.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.start();
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }
    }

    public void placePipes(){
        int randomPipePosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipePosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void checkCollision() {
        Rectangle playerBounds = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (Pipe pipe : pipes) {
            Rectangle pipeBounds = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerBounds.intersects(pipeBounds)) {
                gameOver = true;
                break;
            }
        }

        if (player.getPosY() >= frameHeight - player.getHeight()) {
            gameOver = true;
        }

        if (gameOver) {
            gameLoop.stop();
            pipesCooldown.stop();
        }
    }




    // Metode baru untuk mereset permainan saat tombol "R" ditekan
    public void restartGame() {
        if (gameOver || player.getPosY() >= frameHeight - player.getHeight()) { // Periksa apakah game over dan posisi pemain ada di batas bawah JFrame
            pipes.clear();
            player.setPosY(playerStartPosY);
            score = 0;
            gameOver = false;
            gameLoop.start();
            pipesCooldown.start();
            repaint();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            checkCollision();
            updateScore();
            repaint();
        }
    }

    // Metode untuk menangani penambahan skor dan menampilkan label skor
    public void updateScore() {
        for (int i = 0; i < pipes.size(); i += 2) { // Iterasi hanya setiap dua pipa (karena setiap pipa memiliki bagian atas dan bawah)
            Pipe pipe = pipes.get(i);
            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                score++;
            }
        }
        // Setel teks label skor ke nilai skor yang baru
        scoreLabel.setText("Score: " + score);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player.setVelocityY(-10);
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                restartGame(); // Panggil restartGame() saat tombol "R" ditekan
            }
        }
    }


    // Metode yang tidak digunakan, dapat dihapus
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}