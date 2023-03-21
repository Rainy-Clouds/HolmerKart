package tracks;

import javax.swing.*;
import java.awt.BorderLayout;

public class TrackEditor {
    public static void main(String[] args)
    {
        JFrame editorFrame = new JFrame();
        editorFrame.setTitle("Track Editor");
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.setLayout(new BorderLayout());
        editorFrame.add(new EditPanel(), BorderLayout.CENTER);
        editorFrame.pack();
        editorFrame.setResizable(false);
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setVisible(true);
    }
}
