import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import org.apache.http.util.TextUtils;

public class TransSelectedAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor=e.getData(PlatformDataKeys.EDITOR);
        String selectedWord=editor.getSelectionModel().getSelectedText();
        if(TextUtils.isEmpty(selectedWord)) return;
        TransManager.getInstance().translate(selectedWord,editor);
    }
}
