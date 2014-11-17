package com.example.bookshelf_initialpage;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASS DESCRIPTION TBI 
 * Code adapted from http://appheads.ru/
 * 
 * @author Anjana Chatta
 * 
 */
public class SplitFragment {
	private final int pageWidth;
	private final int pageHeight;
	private final float lineSpacingMultiplier;
	private final int lineSpacingExtra;
	private final List<CharSequence> pages = new ArrayList<CharSequence>();
	private SpannableStringBuilder currentLine = new SpannableStringBuilder();
	private SpannableStringBuilder currentPage = new SpannableStringBuilder();
	private int currentLineHeight;
	private int pageContentHeight;
	private int currentLineWidth;
	private int textLineHeight;

	/**
	 * CONSTRUCTOR DESCRIPTION TBI
	 * 
	 * @param pageWidth
	 *            PARAM DESCRIPTION TBI
	 * @param pageHeight
	 *            PARAM DESCRIPTION TBI
	 * @param lineSpacingMultiplier
	 *            PARAM DESCRIPTION TBI
	 * @param lineSpacingExtra
	 *            PARAM DESCRIPTION TBI
	 */
	public SplitFragment(int pageWidth, int pageHeight,
			float lineSpacingMultiplier, int lineSpacingExtra) {
		this.pageWidth = pageWidth;
		this.pageHeight = pageHeight;
		this.lineSpacingMultiplier = lineSpacingMultiplier;
		this.lineSpacingExtra = lineSpacingExtra;
	}

	/**
	 * METHOD DESCRIPTION TBI
	 * 
	 * @param text
	 *            PARAM DESCRIPTION TBI
	 * @param textPaint
	 *            PARAM DESCRIPTION TBI
	 */
	public void append(String text, TextPaint textPaint) {
		textLineHeight = (int) Math.ceil(textPaint.getFontMetrics(null)
				* lineSpacingMultiplier + lineSpacingExtra);
		String[] paragraphs = text.split("\n", -1);
		int i;
		for (i = 0; i < paragraphs.length - 1; i++) {
			appendText(paragraphs[i], textPaint);
			appendNewLine();
		}
		appendText(paragraphs[i], textPaint);
	}

	/**
	 * METHOD DESCRIPTION TBI
	 * 
	 * @param text
	 *            PARAM DESCRIPTION TBI
	 * @param textPaint
	 *            PARAM DESCRIPTION TBI
	 */
	private void appendText(String text, TextPaint textPaint) {
		String[] words = text.split(" ", -1);
		int i;
		for (i = 0; i < words.length - 1; i++) {
			appendWord(words[i] + " ", textPaint);
		}
		appendWord(words[i], textPaint);
	}

	/**
	 * METHOD DESCRIPTION TBI
	 */
	private void appendNewLine() {
		currentLine.append("\n");
		checkForPageEnd();
		appendLineToPage(textLineHeight);
	}

	/**
	 * METHOD DESCRIPTION TBI
	 */
	private void checkForPageEnd() {
		if (pageContentHeight + currentLineHeight > pageHeight) {
			pages.add(currentPage);
			currentPage = new SpannableStringBuilder();
			pageContentHeight = 0;
		}
	}

	/**
	 * METHOD DESCRIPTION TBI
	 * 
	 * @param appendedText
	 *            PARAM DESCRIPTION TBI
	 * @param textPaint
	 *            PARAM DESCRIPTION TBI
	 */
	private void appendWord(String appendedText, TextPaint textPaint) {
		int textWidth = (int) Math.ceil(textPaint.measureText(appendedText));
		if (currentLineWidth + textWidth >= pageWidth) {
			checkForPageEnd();
			appendLineToPage(textLineHeight);
		}
		appendTextToLine(appendedText, textPaint, textWidth);
	}

	/**
	 * METHOD DESCRIPTION TBI
	 * 
	 * @param textLineHeight
	 *            PARAM DESCRIPTION TBI
	 */
	private void appendLineToPage(int textLineHeight) {
		currentPage.append(currentLine);
		pageContentHeight += currentLineHeight;

		currentLine = new SpannableStringBuilder();
		currentLineHeight = textLineHeight;
		currentLineWidth = 0;
	}

	/**
	 * METHOD DESCRIPTION TBI
	 * 
	 * @param appendedText
	 *            PARAM DESCRIPTION TBI
	 * @param textPaint
	 *            PARAM DESCRIPTION TBI
	 * @param textWidth
	 *            PARAM DESCRIPTION TBI
	 */
	private void appendTextToLine(String appendedText, TextPaint textPaint,
			int textWidth) {
		currentLineHeight = Math.max(currentLineHeight, textLineHeight);
		currentLine.append(renderToSpannable(appendedText, textPaint));
		currentLineWidth += textWidth;
	}

	/**
	 * METHOD DESCRIPTION TBI
	 * 
	 * @return RETURN VALUE DESCRIPTION TBI
	 */
	public List<CharSequence> getPages() {
		List<CharSequence> copyPages = new ArrayList<CharSequence>(pages);
		SpannableStringBuilder lastPage = new SpannableStringBuilder(
				currentPage);
		if (pageContentHeight + currentLineHeight > pageHeight) {
			copyPages.add(lastPage);
			lastPage = new SpannableStringBuilder();
		}
		lastPage.append(currentLine);
		copyPages.add(lastPage);
		return copyPages;
	}

	/**
	 * METHOD DESCRIPTION TBI
	 * 
	 * @param text
	 *            PARAM DESCRIPTION TBI
	 * @param textPaint
	 *            PARAM DESCRIPTION TBI
	 * @return RETURN VALUE DESCRIPTION TBI
	 */
	private SpannableString renderToSpannable(String text, TextPaint textPaint) {
		SpannableString spannable = new SpannableString(text);

		if (textPaint.isFakeBoldText()) {
			spannable.setSpan(new StyleSpan(Typeface.BOLD), 0,
					spannable.length(), 0);
		}
		return spannable;
	}
}
