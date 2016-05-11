## BaseSupportAndroidModel ##

* 提供可快速進行迭代開發的模組，加速頁面的開發
* 目前功能
** 快速建置toolbar畫面
** 快速建置側邊欄畫面
** 可迅速切換畫面，自帶返回頁面堆棧
** 自帶loading畫面
** 快速開啟網頁、圖片、可放大縮小之圖片
** 列表介面

* feature
    登入畫面
    
### install gradle ###

``` 
repositories {
    maven {
            url "https://api.bitbucket.org/1.0/repositories/easyapp/android_module_baseproject/raw/master/"
            credentials {
                username 'Jimroid'
                password 'Kincaid123'
            }
    }
}
```


```
dependencies {
    compile 'com.easyapp.baseproject.lib:BaseSupport:2.1.2'
}
```

### 範例 ###
* 提供基本的toolbar activity

```
    class Activity extends BaseMainActivity{
    
    }
```

* 提供基本側邊欄 activity

```
   class Activity extends BaseDrawerMainActivity{
   
   }
```
 
* 提供onBackPressListener

```
@Override
public void onBackPressed() {
    List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
    if (fragmentList != null) {
        //TODO: Perform your logic to pass back press here
        for(Fragment fragment : fragmentList){
           if(fragment instanceof OnBackPressedListener){
               ((OnBackPressedListener)fragment).onBackPressed();
           }
        }
    }
}
```
