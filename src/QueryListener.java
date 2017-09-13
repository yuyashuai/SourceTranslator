
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.SelectionEvent;
import com.intellij.openapi.editor.event.SelectionListener;
import org.apache.http.util.TextUtils;


public class QueryListener implements SelectionListener, EditorMouseListener {
    @Override
    public void selectionChanged(SelectionEvent selectionEvent) {

        Editor editor=selectionEvent.getEditor();
        String text=editor.getSelectionModel().getSelectedText();
        if(TextUtils.isEmpty(text)) return;
        if(!text.contains(" ")) return;
    }

    @Override
    public void mousePressed(EditorMouseEvent editorMouseEvent) {

    }

    @Override
    public void mouseClicked(EditorMouseEvent editorMouseEvent) {
        Editor editor = editorMouseEvent.getEditor();
        Document document = editor.getDocument();
        if (document.isWritable()) {
            return;
        }
        String selectedText = editorMouseEvent.getEditor().getSelectionModel().getSelectedText();
        if(TextUtils.isEmpty(selectedText)) return;
        if(selectedText.contains(" ")) return;
        TransManager.getInstance().translate(selectedText,editor);
    }

    @Override
    public void mouseReleased(EditorMouseEvent editorMouseEvent) {

    }

    @Override
    public void mouseEntered(EditorMouseEvent editorMouseEvent) {

    }

    @Override
    public void mouseExited(EditorMouseEvent editorMouseEvent) {

    }

}
