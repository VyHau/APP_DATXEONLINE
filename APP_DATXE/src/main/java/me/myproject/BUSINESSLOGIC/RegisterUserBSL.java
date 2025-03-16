package me.myproject.BUSINESSLOGIC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import me.myproject.GUI.RegisterDriverGUI;
import me.myproject.GUI.RegisterUserGUI;
   
public class RegisterUserBSL implements ActionListener,InputMethodListener{
    private RegisterUserGUI view;

    

    public RegisterUserBSL(RegisterUserGUI view) {
        this.view=view;
    }
    @Override
    public void inputMethodTextChanged(InputMethodEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputMethodTextChanged'");
    }

    @Override
    public void caretPositionChanged(InputMethodEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caretPositionChanged'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name=e.getActionCommand();
        System.out.println(name);
        switch (name) {
            case "Đăng ký":
                   
                break;
            case "Quay lại":
                    new RegisterDriverGUI();
                    view.dispose();
                break;
            default:
                // throw new AssertionError();
            
        }
    }

}

