package efesio.com.br.app.widgets.texts;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;

public class DecimalEditText extends AppCompatEditText {
	private boolean isUpdating;

	/*
	 * Maps the cursor position from phone number to masked number... 1234567890
	 * => 12345-678
	 */
	private int positioning[] = { 0, 1, 2, 3, 4, 6, 7, 8, 9 };

	public DecimalEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();

	}

	public DecimalEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();

	}

	public DecimalEditText(Context context) {
		super(context);
		initialize();

	}

	public String getCleanText() {
		String text = DecimalEditText.this.getText().toString();

		text.replaceAll("[^0-9]*", "");
		return text;

	}

	public String getDecimal() {
		String text = DecimalEditText.this.getText().toString();

		text = text.replace("R$", "");
		return text;

	}

	private void initialize() {

		final int maxNumberLength = 8;
		this.setKeyListener(keylistenerNumber);
		this.setInputType(InputType.TYPE_CLASS_NUMBER);
		this.setHint("");

		this.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(!s.toString().matches("^R\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
                {
                    String userInput= ""+s.toString().replaceAll("[^\\d]", "");
                    StringBuilder cashAmountBuilder = new StringBuilder(userInput);

                    while (cashAmountBuilder.length() > 3 && cashAmountBuilder.charAt(0) == '0') {
                        cashAmountBuilder.deleteCharAt(0);
                    }
                    while (cashAmountBuilder.length() < 3) {
                        cashAmountBuilder.insert(0, '0');
                    }
                    cashAmountBuilder.insert(cashAmountBuilder.length()-2, '.');
                    cashAmountBuilder.insert(0, 'R');
                    cashAmountBuilder.insert(1, '$');

                    DecimalEditText.this.setText(cashAmountBuilder.toString());
                    DecimalEditText.this.setTextKeepState(cashAmountBuilder.toString());
                    Selection.setSelection(DecimalEditText.this.getText(), cashAmountBuilder.toString().length());
                }

            }
		});
	}

	protected String padNumber(String number, int maxLength) {
		String padded = new String(number);
		for (int i = 0; i < maxLength - number.length(); i++)
			padded += " ";
		return padded;

	}

	private final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();

	private class KeylistenerNumber extends NumberKeyListener {

		@Override
		public int getInputType() {
			return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;

		}

		@Override
		protected char[] getAcceptedChars() {
			return new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		}
	}
}
