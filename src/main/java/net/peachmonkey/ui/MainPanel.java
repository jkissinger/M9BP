package net.peachmonkey.ui;

import javafx.embed.swing.JFXPanel;
import net.peachmonkey.game.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class MainPanel {

    private static final Logger LOGGER = LogManager.getLogger();
    private JTextArea textArea;
    @Autowired
    private GameState gameState;

    @PostConstruct
    public void init() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }

    private void initAndShowGUI() {
        // This method is invoked on Swing thread
        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setVisible(true);
        frame.setTitle("M9BP - Party Enrichment Application");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dim.width / 2, dim.height / 2);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        JPanel pane = new JPanel();
        frame.setContentPane(pane);
        pane.setLayout(new BorderLayout());

        textArea = new JTextArea("");
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.add(scroll, BorderLayout.CENTER);

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.LINE_AXIS));
        pane.add(listPane, BorderLayout.PAGE_END);

        JButton codeButton = new JButton("Enter Code");
        codeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                enterCode();
            }
        });
        listPane.add(codeButton);
    }

    public void enterCode() {
        String code = JOptionPane.showInputDialog("Enter the code:");
        LOGGER.trace("User entered: " + code);
        gameState.codeEntered(code);
    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            textArea.append(String.valueOf(c));
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                LOGGER.error(e);
                return;
            }
        }
        textArea.append("\n\n");
    }
}
