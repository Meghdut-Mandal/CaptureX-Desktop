package console;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;

public class ConsoleDocument extends DefaultStyledDocument implements CaretListener
{
	private Caret caret;
	
	private static final long serialVersionUID = -1270788544217141905L;

	private Console console = null;

	private int limit;

	private boolean doFocus = true;
	
	public void setConsole(Console console) {
        this.console = console;
    }
    
    public void setFocusAfterAppend(boolean var) {
        doFocus = var;
    }
	
    public void write(String text, MutableAttributeSet attrs)
    {
        try
        {
            insertString(getLength(), text, attrs);
            limit = getLength();
            caret.setDot(limit);
        }
        catch(BadLocationException e)
        {
            e.printStackTrace();
        }

        if (doFocus)
        {
        	console.focus();
		}
    }
    
	public void writeUser(String text, MutableAttributeSet attrs)
	{
		try
		{
			insertString(getLength(), text, attrs);
			caret.setDot(getLength());
		}
		catch(BadLocationException e)
		{
			e.printStackTrace();
		}
        if (doFocus) console.focus();
	}
	
	public String getUserInput()
	{
		try
		{
			return getText(limit, getLength() - limit);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
			return null;
		}
        
	}
	
	@Override
	public void remove(int offs, int len) throws BadLocationException
	{
		if(offs < limit)
		{
			return;
		}
		super.remove(offs, len);
	}

	public void setCaret(Caret caret)
	{
		this.caret = caret;
	}
	
	public int getLimit() {
        return limit;
    }
    
    public boolean isCursorValid() {
        return caret.getDot() >= limit;
    }
    
    public void makeCursorValid() {
        if(caret.getDot() < limit)
         {
             caret.setDot(limit);
         }
    }
    
    public void caretUpdate(CaretEvent e) {} // Moved to "MakeCursorValid" so that the user can still copy text
	
}
