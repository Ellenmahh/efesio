package efesio.com.br.app.widgets.texts;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;


public class PhoneEditText extends MaskedEditText {
	public PhoneEditText(Context ctx) {
		super(ctx);

		initialize();
	}

	public PhoneEditText(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);

		initialize();
	}

	public PhoneEditText(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);

		initialize();
	}

	private void initialize() {
		this.setMask("(99) 99999-9999");
		this.setInputType(InputType.TYPE_CLASS_PHONE);
	}


}
