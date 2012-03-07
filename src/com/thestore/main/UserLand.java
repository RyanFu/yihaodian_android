// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.*;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.cart.CartVO;
import com.yihaodian.mobile.vo.user.VerifyCodeVO;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class UserLand extends ActivityMain
    implements android.view.View.OnClickListener
{

    public UserLand()
    {
        landLayout = null;
        registLayout = null;
        forgetLayout = null;
        modifyLayout = null;
        landUserEditText = null;
        landPasswordEditText = null;
        landButton = null;
        registButton = null;
        forgetButton = null;
        rememberCheckBox = null;
        registUserEditText = null;
        registPassEditText = null;
        registSecondPassEditText = null;
        registCodeEditText = null;
        registSubmitButton = null;
        registCodeImageView = null;
        registCodeTextView = null;
        util = null;
        forgetUserEditText = null;
        forgetCodeEditText = null;
        forgetOkButton = null;
        forgetImageView = null;
        forgeTextView = null;
        oldPwdEditText = null;
        newPwdEditText = null;
        checkPwdEditText = null;
        modifyOkButton = null;
        resultStr = "";
        userName = "";
        oldPwd = "";
        newPwd = "";
        checkPwd = "";
        type = -1;
        checkedChangeListener = new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                isCheck = flag;
            }

            final UserLand this$0;

            
            {
                this$0 = UserLand.this;
                super();
            }
        }
;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296286 2131296299: default 76
            //                           2131296286 77
            //                           2131296287 504
            //                           2131296288 857
            //                           2131296289 971
            //                           2131296290 1094
            //                           2131296291 76
            //                           2131296292 76
            //                           2131296293 76
            //                           2131296294 76
            //                           2131296295 76
            //                           2131296296 76
            //                           2131296297 76
            //                           2131296298 76
            //                           2131296299 319;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L7
_L1:
                return;
_L2:
                if(message.obj != null)
                {
                    if(message.obj.equals("-1"))
                    {
                        cancelProgress();
                        showToast(0x7f0a009e);
                    } else
                    if(message.obj.equals("-2"))
                    {
                        cancelProgress();
                        showToast(0x7f0a009f);
                    } else
                    {
                        User.token = (String)message.obj;
                        setShare(isCheck);
                        if(type == 0x7f09001f)
                        {
                            type = 99;
                            registSuccess();
                        } else
                        {
                            showToast(0x7f0a00a0);
                            User.password = util.getEditString(landPasswordEditText);
                            if(DBHelper.testNet())
                            {
                                CartAsyncTask cartasynctask = new CartAsyncTask(handler, 0x7f09002b);
                                if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                {
                                    cartasynctask.cancel(true);
                                    cartasynctask.execute(null);
                                } else
                                {
                                    cartasynctask.execute(null);
                                }
                            } else
                            {
                                util.showNetNull();
                            }
                        }
                    }
                } else
                {
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L7:
                if(message.obj == null) goto _L9; else goto _L8
_L8:
                Cart.cartTotal = ((CartVO)message.obj).getTotalquantity().longValue();
                setCartImage(Long.valueOf(Cart.cartTotal));
                User.TYPE;
                JVM INSTR tableswitch 2131296256 2131296263: default 404
            //                           2131296256 466
            //                           2131296257 436
            //                           2131296258 421
            //                           2131296259 451
            //                           2131296260 404
            //                           2131296261 481
            //                           2131296262 481
            //                           2131296263 481;
                   goto _L10 _L11 _L12 _L13 _L14 _L10 _L15 _L15 _L15
_L10:
                cancelProgress();
                finish();
                continue; /* Loop/switch isn't completed */
_L13:
                util.changeMain("com.thestore.main.Message");
                continue; /* Loop/switch isn't completed */
_L12:
                util.changeMain("com.thestore.main.Cart");
                continue; /* Loop/switch isn't completed */
_L14:
                util.changeMain("com.thestore.main.feekback");
                continue; /* Loop/switch isn't completed */
_L11:
                util.changeMain("com.thestore.main.Mystore");
                continue; /* Loop/switch isn't completed */
_L15:
                finish();
                if(true) goto _L10; else goto _L9
_L9:
                util.showNetNull();
                continue; /* Loop/switch isn't completed */
_L3:
                if(message.obj != null)
                    if(message.obj.equals("1"))
                    {
                        type = 0x7f09001f;
                        if(DBHelper.testNet())
                        {
                            User.password = util.getEditString(registPassEditText);
                            User.userName = util.getEditString(registUserEditText);
                            UserAsyncTask userasynctask = new UserAsyncTask(User.userName, User.password, handler, 0x7f09001e);
                            if(userasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                userasynctask.cancel(true);
                                userasynctask.execute(null);
                            } else
                            {
                                userasynctask.execute(null);
                            }
                            cancelProgress();
                        } else
                        {
                            cancelProgress();
                            util.showNetNull();
                        }
                    } else
                    if(message.obj.equals("-1"))
                    {
                        cancelProgress();
                        showToast(0x7f0a00a9);
                    } else
                    if(message.obj.equals("-2"))
                    {
                        cancelProgress();
                        showToast(0x7f0a00aa);
                        cancelProgress();
                    } else
                    if(message.obj.equals("-3"))
                    {
                        cancelProgress();
                        showToast(0x7f0a00ab);
                        landUser();
                    } else
                    if(message.obj.equals("-4"))
                    {
                        cancelProgress();
                        showToast(0x7f0a00ac);
                    } else
                    if(message.obj.equals("-5"))
                    {
                        cancelProgress();
                        showToast(0x7f0a00ad);
                    } else
                    {
                        cancelProgress();
                        util.showNetNull();
                    }
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj != null)
                {
                    VerifyCodeVO verifycodevo = (VerifyCodeVO)message.obj;
                    String s = verifycodevo.getCodeUrl();
                    User.tempToken = verifycodevo.getTempToken();
                    BitmapDrawable bitmapdrawable = new BitmapDrawable((new DBHelper()).getBitMap(s));
                    if(registCodeImageView != null)
                        registCodeImageView.setImageDrawable(bitmapdrawable);
                    if(forgetImageView != null)
                        forgetImageView.setImageDrawable(bitmapdrawable);
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L5:
                if(message.obj != null)
                    if(message.obj.equals("1"))
                        showToast(0x7f0a00ba);
                    else
                    if(message.obj.equals("-1"))
                        showToast(0x7f0a00a9);
                    else
                    if(message.obj.equals("-2"))
                        showToast(0x7f0a00ab);
                    else
                    if(message.obj.equals("-3"))
                        showToast(0x7f0a00ac);
                    else
                        util.showNetNull();
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L6:
                if(message.obj != null)
                {
                    if(message.obj.equals("-1"))
                        showToast("\u5BC6\u7801\u9519\u8BEF");
                    else
                    if(message.obj.equals("-2"))
                        showToast("\u5BC6\u7801\u683C\u5F0F\u9519\u8BEF");
                    else
                    if(message.obj.equals("1"))
                    {
                        showToast("\u4FEE\u6539\u6210\u529F");
                        User.password = newPwd;
                        finish();
                    } else
                    {
                        util.showNetNull();
                    }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                if(true) goto _L1; else goto _L16
_L16:
            }

            final UserLand this$0;

            
            {
                this$0 = UserLand.this;
                super();
            }
        }
;
    }

    private String checkModify()
    {
        oldPwd = oldPwdEditText.getText().toString().trim();
        newPwd = newPwdEditText.getText().toString().trim();
        checkPwd = checkPwdEditText.getText().toString().trim();
        String s;
        if(oldPwd.length() == 0)
        {
            newPwdEditText.setText("");
            checkPwdEditText.setText("");
            oldPwdEditText.requestFocus();
            s = "\u539F\u59CB\u5BC6\u7801\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        } else
        if(!oldPwd.equals(User.password))
        {
            newPwdEditText.setText("");
            checkPwdEditText.setText("");
            oldPwdEditText.requestFocus();
            s = "\u539F\u59CB\u5BC6\u7801\u4E0D\u6B63\u786E\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01";
        } else
        if(newPwd.length() == 0)
        {
            checkPwdEditText.setText("");
            newPwdEditText.requestFocus();
            s = "\u65B0\u5BC6\u7801\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        } else
        if(newPwd.length() <= 5 || newPwd.length() >= 21)
        {
            checkPwdEditText.setText("");
            newPwdEditText.requestFocus();
            s = "\u5BC6\u7801\u4E3A6-20\u4F4D\uFF01";
        } else
        if(checkPwd.length() == 0)
        {
            checkPwdEditText.setText("");
            checkPwdEditText.requestFocus();
            s = "\u786E\u8BA4\u5BC6\u7801\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        } else
        if(!checkPwd.equals(newPwd))
        {
            checkPwdEditText.setText("");
            checkPwdEditText.requestFocus();
            s = "\u4E8C\u6B21\u5BC6\u7801\u8F93\u5165\u4E0D\u4E00\u81F4\uFF01\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01";
        } else
        {
            s = "";
        }
        return s;
    }

    private boolean getIsCheck()
    {
        return getSharedPreferences().getBoolean("check", false);
    }

    private String getPassword()
    {
        return getSharedPreferences().getString("password", "");
    }

    private String getShareString()
    {
        return getSharedPreferences().getString("user", "");
    }

    private SharedPreferences getSharedPreferences()
    {
        return getSharedPreferences("user", 0);
    }

    private void initForget()
    {
        forgetUserEditText = (EditText)findViewById(0x7f0901f6);
        forgetCodeEditText = (EditText)findViewById(0x7f0901f8);
        forgetImageView = (ImageView)findViewById(0x7f0901f9);
        forgeTextView = (TextView)findViewById(0x7f0901fa);
        forgeTextView.setOnClickListener(this);
        forgetOkButton = (Button)findViewById(0x7f0901fb);
        forgetOkButton.setOnClickListener(this);
    }

    private void initLand()
    {
        landLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a0096);
        landUserEditText = (EditText)findViewById(0x7f0901c8);
        landUserEditText.setText(getShareString());
        landPasswordEditText = (EditText)findViewById(0x7f0901c9);
        landPasswordEditText.setText(getPassword());
        rememberCheckBox = (CheckBox)findViewById(0x7f0901e4);
        rememberCheckBox.setChecked(getIsCheck());
        landButton = (Button)findViewById(0x7f0901e5);
        registButton = (Button)findViewById(0x7f0901e7);
        forgetButton = (Button)findViewById(0x7f0901e8);
        landButton.setOnClickListener(this);
        registButton.setOnClickListener(this);
        forgetButton.setOnClickListener(this);
        rememberCheckBox.setOnCheckedChangeListener(checkedChangeListener);
    }

    private void initLayout()
    {
        landLayout = (LinearLayout)findViewById(0x7f0901e2);
        registLayout = (LinearLayout)findViewById(0x7f0901e9);
        forgetLayout = (LinearLayout)findViewById(0x7f0901f3);
        modifyLayout = (LinearLayout)findViewById(0x7f0901fc);
    }

    private void initModify()
    {
        modifyLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a0051);
        oldPwdEditText = (EditText)findViewById(0x7f0901fd);
        newPwdEditText = (EditText)findViewById(0x7f0901fe);
        checkPwdEditText = (EditText)findViewById(0x7f0901ff);
        modifyOkButton = (Button)findViewById(0x7f090200);
        modifyOkButton.setOnClickListener(this);
    }

    private void initRegist()
    {
        registUserEditText = (EditText)findViewById(0x7f0901ea);
        registCodeImageView = (ImageView)findViewById(0x7f0901ef);
        registCodeTextView = (TextView)findViewById(0x7f0901f0);
        registCodeTextView.setOnClickListener(this);
        registPassEditText = (EditText)findViewById(0x7f0901eb);
        registCodeEditText = (EditText)findViewById(0x7f0901ee);
        registSecondPassEditText = (EditText)findViewById(0x7f0901ec);
        registSubmitButton = (Button)findViewById(0x7f0901f2);
        registSubmitButton.setOnClickListener(this);
    }

    private void landUser()
    {
        initLand();
        registLayout.setVisibility(8);
        landLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a0096);
        landUserEditText.setText(util.getEditString(registUserEditText));
    }

    private void registSuccess()
    {
        String s = (new StringBuilder()).append(getResources().getString(0x7f0a00b0)).append(User.userName).toString();
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0a00c0).setMessage(s).setIcon(0x7f020068).setPositiveButton(0x7f0a00ae, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                util.changeMain("com.thestore.main.Type");
            }

            final UserLand this$0;

            
            {
                this$0 = UserLand.this;
                super();
            }
        }
).setNegativeButton(0x7f0a00af, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                util.changeMain("com.thestore.main.Mystore");
            }

            final UserLand this$0;

            
            {
                this$0 = UserLand.this;
                super();
            }
        }
).show();
    }

    private void setForgetMenu(boolean flag)
    {
        menu.findItem(0x7f09021e).setVisible(flag);
        menu.findItem(0x7f09021c).setVisible(flag);
    }

    private void setLoginMenu(boolean flag)
    {
        menu.findItem(0x7f09021c).setVisible(flag);
        menu.findItem(0x7f09021d).setVisible(flag);
        menu.findItem(0x7f090202).setVisible(flag);
    }

    private void setMenuIsShown(boolean flag)
    {
        menu.findItem(0x7f09021c).setVisible(flag);
        menu.findItem(0x7f09021d).setVisible(flag);
        menu.findItem(0x7f090202).setVisible(flag);
        menu.findItem(0x7f09021e).setVisible(flag);
    }

    private void setModifyMenu(boolean flag)
    {
        menu.findItem(0x7f090202).setVisible(flag);
    }

    private void setRegistMenu(boolean flag)
    {
        menu.findItem(0x7f09021e).setVisible(flag);
        menu.findItem(0x7f090202).setVisible(flag);
    }

    private void setShare(boolean flag)
    {
        if(flag)
        {
            getSharedPreferences().edit().putString("password", util.getEditString(landPasswordEditText)).commit();
            getSharedPreferences().edit().putString("user", util.getEditString(landUserEditText)).commit();
        } else
        {
            getSharedPreferences().edit().putString("user", "").commit();
            getSharedPreferences().edit().putString("password", "").commit();
        }
        getSharedPreferences().edit().putBoolean("check", flag).commit();
    }

    private void showEmpty(EditText edittext, int i)
    {
        if(util.isEmpty(edittext))
        {
            showToast(i);
            util.showInputMethod(edittext);
        }
    }

    private void showFocus(EditText edittext, int i)
    {
        if(util.isEmpty(edittext))
        {
            showToast(i);
            util.showInputMethod(edittext);
        }
    }

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR lookupswitch 8: default 80
    //                   2131296741: 201
    //                   2131296743: 490
    //                   2131296744: 385
    //                   2131296752: 595
    //                   2131296754: 669
    //                   2131296762: 595
    //                   2131296763: 929
    //                   2131296768: 86;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L5 _L7 _L8
_L1:
        super.onClick(view);
        return;
_L8:
        resultStr = checkModify();
        if(!resultStr.equals(""))
        {
            showToast(resultStr);
        } else
        {
            UserAsyncTask userasynctask6 = new UserAsyncTask(userName, oldPwdEditText.getText().toString().trim(), newPwdEditText.getText().toString().trim(), handler, 0x7f090022);
            if(userasynctask6.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                userasynctask6.cancel(true);
                userasynctask6.execute(null);
            } else
            {
                userasynctask6.execute(null);
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        util.hindInputMethod();
        if(!util.isEmpty(landUserEditText))
            showEmpty(landPasswordEditText, 0x7f0a009d);
        else
            showEmpty(landUserEditText, 0x7f0a009c);
        if(!util.isEmpty(landUserEditText) && !util.isEmpty(landPasswordEditText))
            if(DBHelper.testNet())
            {
                showProgress();
                User.userAccount = util.getEditString(landUserEditText);
                UserAsyncTask userasynctask5 = new UserAsyncTask(util.getEditString(landUserEditText), util.getEditString(landPasswordEditText), handler, 0x7f09001e);
                if(userasynctask5.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    userasynctask5.cancel(true);
                    userasynctask5.execute(null);
                } else
                {
                    userasynctask5.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
        continue; /* Loop/switch isn't completed */
_L4:
        initForget();
        landLayout.setVisibility(8);
        forgetLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a00b1);
        if(DBHelper.testNet())
        {
            showProgress();
            UserAsyncTask userasynctask4 = new UserAsyncTask(0x7f090020, handler);
            if(userasynctask4.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                userasynctask4.cancel(true);
                userasynctask4.execute(null);
            } else
            {
                userasynctask4.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        initRegist();
        landLayout.setVisibility(8);
        registLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a00a1);
        if(DBHelper.testNet())
        {
            showProgress();
            UserAsyncTask userasynctask3 = new UserAsyncTask(0x7f090020, handler);
            if(userasynctask3.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                userasynctask3.cancel(true);
                userasynctask3.execute(null);
            } else
            {
                userasynctask3.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(DBHelper.testNet())
        {
            showProgress();
            UserAsyncTask userasynctask2 = new UserAsyncTask(0x7f090020, handler);
            if(userasynctask2.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                userasynctask2.cancel(true);
                userasynctask2.execute(null);
            } else
            {
                userasynctask2.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(!util.isEmpty(registUserEditText))
        {
            if(!util.isEmpty(registPassEditText))
            {
                if(util.isEmpty(registSecondPassEditText))
                    showFocus(registSecondPassEditText, 0x7f0a009d);
                else
                if(util.isEmpty(registCodeEditText))
                    showFocus(registCodeEditText, 0x7f0a00b8);
                else
                if(!util.getEditString(registPassEditText).equals(util.getEditString(registSecondPassEditText)))
                    showToast(0x7f0a00a6);
                else
                if(DBHelper.testNet())
                {
                    User.code = util.getEditString(registCodeEditText);
                    showProgress();
                    UserAsyncTask userasynctask1 = new UserAsyncTask(util.getEditString(registUserEditText), util.getEditString(registPassEditText), handler, 0x7f09001f);
                    if(userasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        userasynctask1.cancel(true);
                        userasynctask1.execute(null);
                    } else
                    {
                        userasynctask1.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            } else
            {
                showEmpty(registPassEditText, 0x7f0a009d);
            }
        } else
        {
            showFocus(registUserEditText, 0x7f0a009c);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if(!util.isEmpty(forgetUserEditText))
            showEmpty(forgetCodeEditText, 0x7f0a00b8);
        else
            showEmpty(forgetUserEditText, 0x7f0a009c);
        if(!util.isEmpty(forgetCodeEditText) && !util.isEmpty(forgetUserEditText))
            if(DBHelper.testNet())
            {
                User.code = util.getEditString(forgetCodeEditText);
                String s = util.getEditString(forgetUserEditText);
                showProgress();
                UserAsyncTask userasynctask = new UserAsyncTask(s, handler, 0x7f090021);
                if(userasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    userasynctask.cancel(true);
                    userasynctask.execute(null);
                } else
                {
                    userasynctask.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
        if(true) goto _L1; else goto _L9
_L9:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030048);
        bottomOnClick(this);
        isCheck = getIsCheck();
        Intent intent = getIntent();
        type = intent.getIntExtra("VIEW_ID", 0);
        if(type == 0x7f0901fc)
        {
            userName = intent.getStringExtra("USER_NAME");
            initLayout();
            initModify();
        } else
        {
            initLayout();
            initLand();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu1)
    {
        menu = menu1;
        (new MenuInflater(this)).inflate(0x7f0c000e, menu1);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4 && !landLayout.isShown() && type != 0x7f0901fc)
        {
            util.setDefaultTitle(0x7f0a0096);
            landLayout.setVisibility(0);
            registLayout.setVisibility(8);
            forgetLayout.setVisibility(8);
            modifyLayout.setVisibility(8);
            flag = false;
        } else
        if(i == 4 && modifyLayout.isShown())
        {
            finish();
            flag = false;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 4: default 48
    //                   2131296770: 50
    //                   2131296796: 109
    //                   2131296797: 266
    //                   2131296798: 223;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return true;
_L2:
        if(landLayout.isShown())
        {
            finish();
        } else
        {
            modifyLayout.setVisibility(8);
            registLayout.setVisibility(8);
            forgetLayout.setVisibility(8);
            initLand();
            landLayout.setVisibility(0);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        initRegist();
        landLayout.setVisibility(8);
        forgetLayout.setVisibility(8);
        registLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a00a1);
        if(DBHelper.testNet())
        {
            showProgress();
            UserAsyncTask userasynctask1 = new UserAsyncTask(0x7f090020, handler);
            if(userasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                userasynctask1.cancel(true);
                userasynctask1.execute(null);
            } else
            {
                userasynctask1.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        continue; /* Loop/switch isn't completed */
_L5:
        initLand();
        registLayout.setVisibility(8);
        forgetLayout.setVisibility(8);
        landLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a0096);
        continue; /* Loop/switch isn't completed */
_L4:
        initForget();
        landLayout.setVisibility(8);
        registLayout.setVisibility(8);
        forgetLayout.setVisibility(0);
        util.setDefaultTitle(0x7f0a00b1);
        if(DBHelper.testNet())
        {
            showProgress();
            UserAsyncTask userasynctask = new UserAsyncTask(0x7f090020, handler);
            if(userasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                userasynctask.cancel(true);
                userasynctask.execute(null);
            } else
            {
                userasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        if(true) goto _L1; else goto _L6
_L6:
    }

    public boolean onPrepareOptionsMenu(Menu menu1)
    {
        setMenuIsShown(false);
        if(!landLayout.isShown()) goto _L2; else goto _L1
_L1:
        setLoginMenu(true);
_L4:
        return super.onPrepareOptionsMenu(menu1);
_L2:
        if(registLayout.isShown())
            setRegistMenu(true);
        else
        if(forgetLayout.isShown())
            setForgetMenu(true);
        else
        if(modifyLayout.isShown())
            setModifyMenu(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
        initLand();
    }

    String checkPwd;
    private EditText checkPwdEditText;
    private android.widget.CompoundButton.OnCheckedChangeListener checkedChangeListener;
    private TextView forgeTextView;
    private Button forgetButton;
    private EditText forgetCodeEditText;
    private ImageView forgetImageView;
    private LinearLayout forgetLayout;
    private Button forgetOkButton;
    private EditText forgetUserEditText;
    Handler handler;
    private boolean isCheck;
    private Button landButton;
    private LinearLayout landLayout;
    private EditText landPasswordEditText;
    private EditText landUserEditText;
    private Menu menu;
    private LinearLayout modifyLayout;
    private Button modifyOkButton;
    String newPwd;
    private EditText newPwdEditText;
    String oldPwd;
    private EditText oldPwdEditText;
    private Button registButton;
    private EditText registCodeEditText;
    private ImageView registCodeImageView;
    private TextView registCodeTextView;
    private LinearLayout registLayout;
    private EditText registPassEditText;
    private EditText registSecondPassEditText;
    private Button registSubmitButton;
    private EditText registUserEditText;
    private CheckBox rememberCheckBox;
    private String resultStr;
    private int type;
    private String userName;
    private Util util;



/*
    static boolean access$002(UserLand userland, boolean flag)
    {
        userland.isCheck = flag;
        return flag;
    }

*/





/*
    static int access$202(UserLand userland, int i)
    {
        userland.type = i;
        return i;
    }

*/







}
