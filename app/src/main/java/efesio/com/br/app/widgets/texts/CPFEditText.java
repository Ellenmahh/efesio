package efesio.com.br.app.widgets.texts;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;

public class CPFEditText extends MaskedEditText {

	public CPFEditText(Context ctx) {
		super(ctx);

		initialize();
	}

	public CPFEditText(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);

		initialize();
	}

	public CPFEditText(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);

		initialize();
	}

    private void initialize() {
		this.setHint("CPF");
		this.setMask("999.999.999-99");
		this.setInputType(InputType.TYPE_CLASS_NUMBER);
	}

	public String getCPF() {
		return this.getText().toString();
	}

}
