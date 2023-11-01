package com.example.encrypt_decrypt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.ClipboardManager;

public class encryption extends AppCompatActivity {
    private Button b;
    private ImageButton ib;
    private EditText e, d, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        b = findViewById(R.id.encrypt_btn);
        e = findViewById(R.id.message_encrypt);
        d = findViewById(R.id.result_encrypt);
        ib = findViewById(R.id.imageButton);
        s = findViewById(R.id.key_e);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = e.getText().toString();
                String key = s.getText().toString();
                String encryptedText = encrypt(text, key);
                d.setText(encryptedText);
            }
        });

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encryptedText = d.getText().toString();
                copyToClipboard(encryptedText);
                Toast.makeText(encryption.this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String encrypt(String text, String key) {
        // Your encryption logic goes here
        // Implement your encryption algorithm and return the encrypted text
        // For example, a simple Caesar cipher:
        int shift = Integer.parseInt(key);
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encryptedText.append((char) (((c - base + shift) % 26) + base));
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Encrypted Text", text);
        clipboard.setPrimaryClip(clip);
    }
}
