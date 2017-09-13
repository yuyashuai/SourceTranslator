
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.*;
import org.jetbrains.annotations.NotNull;


public class DictionaryComponent implements ApplicationComponent {
    private QueryListener queryListener;

    public DictionaryComponent() {
    }

    @Override
    public void initComponent() {
        queryListener = new QueryListener();
        System.out.println("component初始化--------");
        EditorEventMulticaster eventMulticaster = EditorFactory.getInstance().getEventMulticaster();
        eventMulticaster.addSelectionListener(queryListener);
        eventMulticaster.addEditorMouseListener(queryListener);
    }

    @Override
    public void disposeComponent() {
        System.out.println("component结束--------");
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "DictionaryComponent";
    }


}
