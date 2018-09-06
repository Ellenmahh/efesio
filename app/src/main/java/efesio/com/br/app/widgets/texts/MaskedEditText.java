package efesio.com.br.app.widgets.texts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;


public class MaskedEditText extends BaseTextEdit {

	private Mask mask = new Mask(null);
	private boolean _isUpdating = false;

	public String getMask() {
		return this.mask.getMask();
	}

	public void setMask(String value) {
		this.mask.setMask(value);
	}

	public MaskedEditText(Context ctx) {
		super(ctx);
		initialize();
	}

	public MaskedEditText(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		initialize();
	}

	public MaskedEditText(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);
		initialize();

	}

	private void initialize() {
		this.addTextChangedListener(_textWatcher);
	}

	public void clearMask(){
		this.removeTextChangedListener(_textWatcher);
		this.mask = new Mask(null);
		this.setText("");
		this.addTextChangedListener(_textWatcher);
	}

	private TextWatcher _textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			System.out.println("s = [" + s + "], start = [" + start + "], before = [" + before + "], count = [" + count + "]");
			try {
				if (count == 0)
					return;

				if (getMask() == null || getMask().isEmpty())
					return;

				if (_isUpdating) {
					_isUpdating = false;
					return;
				}

				String result = mask.mask(s.toString());

				_isUpdating = true;
				setText(result);
				if (s.toString().length() == 0)
					setSelection(0, result.length());
				else
					setSelection(result.length());

			} catch (Exception ex) {
				if (ex.getMessage() == null)
					return;
				Log.e(getClass().getName(), ex.getMessage());
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {


		}
	};

}
