## BaseSupportAndroidModel ##

* 提供可簡單繼承的activity 及 fragment

### install gradle ###

    repositories {
    maven {
            url "https://api.bitbucket.org/1.0/repositories/easyapp/android_module_baseproject/raw/master/"
            credentials {
                username 'Jimroid'
                password 'Kincaid123'
            }
        }
}


    dependencies {
    compile 'com.easyapp.baseproject.lib:BaseSupport:2.1.2'
}

### Simple ###
* extends BaseSupportActivity
* extends BaseEasyFragment
 

