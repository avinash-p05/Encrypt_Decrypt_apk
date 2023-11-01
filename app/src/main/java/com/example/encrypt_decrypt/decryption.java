package com.example.encrypt_decrypt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.ClipboardManager;

public class decryption extends AppCompatActivity {
    private Button decryptButton;
    private ImageButton copyButton;
    private EditText inputText;
    private EditText keyText;
    private EditText resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        decryptButton = findViewById(R.id.decrypt_btn);
        copyButton = findViewById(R.id.imageButton);
        inputText = findViewById(R.id.message_decrypt);
        keyText = findViewById(R.id.key_d);
        resultText = findViewById(R.id.result_decrypt);

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = inputText.getText().toString();
                String key = keyText.getText().toString();
                String decryptedText = decrypt(text, key);
                resultText.setText(decryptedText);
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String decryptedText = resultText.getText().toString();
                copyToClipboard(decryptedText);
                Toast.makeText(decryption.this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String decrypt(String text, String key) {
        // Your decryption logic goes here
        // Implement your decryption algorithm and return the decrypted text
        // For example, a simple Caesar cipher:
        int shift = Integer.parseInt(key);
        StringBuilder decryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                decryptedText.append((char) (((c - base - shift + 26) % 26 + 26) % 26 + base));
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Decrypted Text", text);
        clipboard.setPrimaryClip(clip);
    }
}
