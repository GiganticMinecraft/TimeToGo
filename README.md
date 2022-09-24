# TimeToGo

指定した曜日・時刻以降にログインしているプレイヤーのゲームモードを同曜に指定したものに変更するプラグインです。

## Details

* 指定した曜日・時刻以降にログインしているプレイヤーは指定したゲームモードに変更されます。
  * 例：[以下のyml](#How-to-write-config)であれば
    * 月曜日以外はすべて変更されない
    * 月曜日であれば以下に従う
      * 0:00〜9:59  (変更されない)
      * 10:00〜10:59  サバイバル
      * 11:00〜11:59  クリエイティブ
      * 12:00〜23:59  スペクテイター
* ゲームモードの変更は以下のタイミングで行われます。
  * ゲームサーバーが起動してから1分ごと
  * プレイヤーがログインしたとき
* `timetogo.ignore`という権限を持っているプレイヤーのゲームモードは変更されません。
  * 初期設定ではOPを所持していても、この権限は付与されません。

## How to write config

* `config.yml`に記載します。
* ゲームモード(`game-mode`), 曜日の配列(`days-of-week`)、時刻(`time`)の組み合わせを配列で記載します。
  * ゲームモードには以下の値のうちのいずれか1つのみ指定できます。
    * `Survival`
    * `Creative`
    * `Spectator`
    * `Adventure`
  * 曜日は
    * 大文字・小文字は区別されるので注意してください。
    * 指定しなかったもしくは空の場合はすべての曜日が指定されたとみなします。
    * 以下の値を配列で指定できます。
      * `Monday`
      * `Tuesday`
      * `Wednesday`
      * `Thursday`
      * `Friday`
      * `Saturday`
      * `Sunday`
  * 時刻は
      * 「`HH:mm`」の形式です。
        * 1桁の数字を指定する場合は、十の位に0を指定するのを忘れないでください。(例: 「1:00」->「01:00」)
      * 秒以下には対応していません。
      * クォーテーションで囲ってください。

```yaml
mode-triggers:
  - game-mode: Survival
    days-of-week:
      - Monday
    time: '10:00'
  - game-mode: Creative
    days-of-week:
      - Monday
    time: '11:00'
  - game-mode: Spectator
    days-of-week:
      - Monday
    time: '12:00'
```

## Command

コマンドは`/ttg`か`/timetogo`をコマンド名とすることで実行できます。

| command       | description  | permission         | default permission |
|---------------|--------------|--------------------|--------------------|
| `/ttg reload` | 設定を再読み込みします。 | `timetogo.command` | OP                 |
| `/ttg list`   | 設定の一覧を表示します。 |||

## Dependencies

* Spigot
  * 開発上は1.16.4を想定していますが、バージョンに依存するコードは基本的に含まれていないため、どのバージョンでも動作すると思われます。
  * ただし、このプラグインがサポートする4つのゲームモードがすべて実装されているのは1.8以降ですので、1.8より古いバージョンでは読み込まれないと思われます。

## License

[GPLv3](./LICENSE)
