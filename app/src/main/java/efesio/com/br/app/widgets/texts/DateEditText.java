package efesio.com.br.app.widgets.texts;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class DateEditText extends android.support.v7.widget.AppCompatEditText implements DatePickerDialog.OnDateSetListener {
	private DatePickerDialog datePickerDialog;
	private LocalDate value;

	public DateEditText(Context ctx) {
		super(ctx);
		initialize();
	}

	public DateEditText(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		initialize();
	}

	public DateEditText(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);
		initialize();
	}

	private void initialize() {
		LocalDateTime dt = LocalDateTime.now();
		datePickerDialog = new DatePickerDialog(getContext(), this, dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth());
		setShowSoftInputOnFocus(false);
		setCursorVisible(false);
		setKeyListener(null);


		setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				datePickerDialog.show();
			}
		});

		setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus)
					datePickerDialog.show();
			}
		});

	}


	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		System.out.println("view = [" + view + "], year = [" + year + "], month = [" + month + "], dayOfMonth = [" + dayOfMonth + "]");
		setValue(new LocalDate(year, month+1, dayOfMonth));
	}

	private void updateText(){
		if (value != null)
			setText(value.toString("dd/MM/yyyy"));
		else
			setText("");
	}

	public LocalDate getValue() {
		return value;
	}

	public void setValue(LocalDate value) {
		this.value = value;
		updateText();
	}
}
