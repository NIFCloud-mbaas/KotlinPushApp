# 【Android】プッシュ通知を組み込もう！
*2016/09/27作成（2017/10/13更新）*

<center><img src="readme-img/OverView.png" alt="画像1" width="700px"></center>

## 概要

* [ニフクラ mobile backend](https://mbaas.nifcloud.com/)のプッシュ通知機能は、Googleが提供しているFirebase Cloud Messaging（以下、FCM）と連携することで、通知の配信を行っています

※ FCMはGCM(Google Cloud Messaging)の新バージョンです。
既にGCMにてプロジェクトの作成・GCMの有効化設定を終えている場合は、継続してご利用いただくことが可能です。

* Androidアプリでプッシュ通知を受信するまでの設定は以下のような流れとなっています
 * FCMプロジェクトの作成とAPIキーの取得
 * ニフクラ mobile backendでの設定
 * アプリでの設定
* 詳しい設定内容は[プッシュ通知（Android）](https://mbaas.nifcloud.com/doc/current/push/basic_usage_android.html)をご参照ください

## ニフクラ mobile backendって何？？
スマートフォンアプリのバックエンド機能（プッシュ通知・データストア・会員管理・ファイルストア・SNS連携・位置情報検索・スクリプト）が**開発不要**、しかも基本**無料**(注1)で使えるクラウドサービス！今回はデータストアを体験します

注1：詳しくは[こちら](https://mbaas.nifcloud.com/price.htm)をご覧ください

<center><img src="readme-img/SdkTypes.png" alt="画像2" width="700px"></center>


## 動作環境
* Windows 7 Professional
* Android Studio v2.2.3
* Android ver 4x,5x,6x,7x

※上記内容で動作確認をしています

<div style="page-break-before:always"></div>

## 手順
### 0.プッシュ通知機能を使うための準備

ニフクラ mobile backendと連携させるためのAPIキーを取得する必要があります。 以下のドキュメントを参考に、FCMプロジェクトの作成とAPIキーの取得を行ってください。

__▼ mobile backendとFCMの連携に必要な設定 ▼__<br>https://mbaas.nifcloud.com/doc/current/tutorial/push_setup_android.html

### 1. ニフクラ mobile backend の準備

* 上記リンクから会員登録（無料）をします
* 登録後、ログインをすると下図のように「アプリの新規作成」画面が出ますので、アプリを作成します

<center><img src="readme-img/mBassNewProject.png" alt="画像3" width="600px"></center>

* アプリ作成されると下図のような画面になります
* この２種類のAPIキー（アプリケーションキーとクライアントキー）は先ほどインポートしたAndroidStudioで作成するAndroidアプリにニフクラ mobile backendの紐付けるため、あとで使います

<center><img src="readme-img/mBassAPIkey.png" alt="画像4" width="600px"></center>

* 動作確認後、端末情報が保存される場所（データストアのinstallationクラス）を確認しておきましょう

<center><img src="readme-img/mBassData.png" alt="画像5" width="600px"></center>

<div style="page-break-before:always"></div>

* アプリ設定を開いてプッシュ通知の設定をします
   * 「プッシュ通知の許可」で「許可する」選択、「保存する」をクリックします
   * 「Androidプッシュ通知」の「APIキー」には、FCMでプロジェクト作成時に発行された「サーバーキー」を記入し、「保存する」をクリックします

<center><img src="readme-img/mBassPushEnv.png" alt="画像6" width="600px"></center>

### 2. GitHub からサンプルプロジェクトのダウンロード

* プロジェクトの [Github ページ](https://github.com/NIFCloud-mbaas/KotlinPushApp)から「 Clone or download 」＞「 Download ZIP 」をクリックします
* プロジェクトを解凍します

### 3. AndroidStudio でアプリを起動

* AndroidStudio を開き、解凍したプロジェクトを選択します

<center><img src="readme-img/SelectProject.png" alt="画像7" width="500px"></center>

<div style="page-break-before:always"></div>

* プロジェクトを開きます

<center><img src="readme-img/ProjectDesign.png" alt="画像8" width="600px"></center>

### 4. APIキーの設定

* `MainActivity.kt`を編集します
* 先程[ニフクラ mobile backend](https://mbaas.nifcloud.com/)のダッシュボード上で確認したAPIキーを貼り付けます

<center><img src="readme-img/AndroidAPIkey.png" alt="画像9" width="600px"></center>

* それぞれ`YOUR_APPLICATION_KEY`と`YOUR_CLIENT_KEY`の部分を書き換えます
 * このとき、ダブルクォーテーション（`"`）を消さないように注意してください！

### 5. AndroidのSender IDキーの設定

* `MainActivity.kt`を編集します

<center><img src="readme-img/FCMAPIkey.png" alt="画像10" width="600px"></center>

<div style="page-break-before:always"></div>

* `ANDROID_SENDER_ID`の部分を、FCMでプロジェクト作成時に発行された「Sender ID」に書き換えます
 * このとき、ダブルクォーテーション（`"`）を消さないように注意してください！

### 6. 動作確認

* AndroidStudioからビルドする。
 * 「プロジェクト場所」`\app\build\outputs\apk\ ***.apk` ファイルが生成される

* インストールしたアプリを起動します
 * プッシュ通知の許可を求めるアラートが出たら、必ず許可してください！

<center><img src="readme-img/Action1.png" alt="画像11" width="200px"></center>

* 起動されたらこの時点でAndroid端末はレジスタレーションIDが取得されます
* [ニフクラ mobile backend](https://mbaas.nifcloud.com/)のダッシュボードから「データストア (installationクラス(端末情報))」を確認してみましょう！

<center><img src="readme-img/Action2.png" alt="画像12" width="700px"></center>

* 端末側で起動したアプリは一度閉じておきます

<div style="page-break-before:always"></div>

### 7. プッシュ通知を送りましょう！

* いよいよです！実際にプッシュ通知を送ってみましょう！
* ニフクラ mobile backendのダッシュボードで「プッシュ通知」＞「＋新しいプッシュ通知」をクリックします
* プッシュ通知のフォームが開かれます
* 必要な項目を入力してプッシュ通知を作成します

<center><img src="readme-img/Action3.png" alt="画像13" width="700px"></center>

* Android端末からプッシュ通知を確認しましょう！
* 少し待つとプッシュ通知が届きます！

<center><img src="readme-img/Action4.png" alt="画像14" width="200px"></center>

* ダッシュボードからのプッシュ通知結果も確認する

<center><img src="readme-img/Action5.png" alt="画像15" width="430px"></center>

<div style="page-break-before:always"></div>

## 解説
サンプルプロジェクトに実装済みの内容のご紹介

#### SDKのインポートと初期設定
* ニフクラ mobile backend の[ドキュメント（クイックスタート）](https://mbaas.nifcloud.com/doc/current/introduction/quickstart_android.html)をご用意していますので、ご活用ください

#### ロジック
* `activity_main.xml`でデザインを作成し、`MainActivity.kt`にロジックを書いています
*  installationクラス(端末情報)が保存される処理は以下のように記述されます

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //**************** APIキーの設定とSDKの初期化 **********************
    NCMB.initialize(this.applicationContext, "YOUR_APPLICATION_KEY", "YOUR_CLIENT_KEY");
    setContentView(R.layout.activity_main)
}
```

## 参考
* ニフクラ mobile backend の[ドキュメント（プッシュ通知（Android）](https://mbaas.nifcloud.com/doc/current/push/basic_usage_android.html)をご用意していますので、ご活用ください
