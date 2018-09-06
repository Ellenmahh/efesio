package efesio.com.br.app.widgets.texts;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;

public class CNPJEditText extends MaskedEditText {

	public CNPJEditText(Context ctx) {
		super(ctx);

		initialize();
	}

	public CNPJEditText(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);

		initialize();
	}

	public CNPJEditText(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);

		initialize();
	}

    private void initialize() {
		this.setHint("CNPJ");
		this.setMask("99.999.999/9999-99");
		this.setInputType(InputType.TYPE_CLASS_NUMBER);
	}

	public String getCPF() {
		return this.getText().toString();
	}

}
