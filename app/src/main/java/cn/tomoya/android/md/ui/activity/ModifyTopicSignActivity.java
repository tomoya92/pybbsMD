package cn.tomoya.android.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tomoya.android.md.R;
import cn.tomoya.android.md.model.storage.SettingShared;
import cn.tomoya.android.md.ui.base.StatusBarActivity;
import cn.tomoya.android.md.ui.listener.NavigationFinishClickListener;
import cn.tomoya.android.md.ui.util.ThemeUtils;

public class ModifyTopicSignActivity extends StatusBarActivity implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.edt_content)
    protected EditText edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppThemeLight, R.style.AppThemeDark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_topic_sign);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));
        toolbar.inflateMenu(R.menu.modify_topic_sign);
        toolbar.setOnMenuItemClickListener(this);

        edtContent.setText(SettingShared.getTopicSignContent(this));
        edtContent.setSelection(edtContent.length());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_default:
                edtContent.setText(SettingShared.DEFAULT_TOPIC_SIGN_CONTENT);
                edtContent.setSelection(edtContent.length());
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SettingShared.setTopicSignContent(this, edtContent.getText().toString());
    }

}
