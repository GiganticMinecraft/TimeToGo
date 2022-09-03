# TimeToGo

指定した時刻以降にログインしているプレイヤーのゲームモードを、同時に指定したものに変更するプラグインです。

## Details

* 指定した時刻以降にログインしているプレイヤーは指定したゲームモードに変更されます。
  * 例：[以下のyml](#How to write config)であれば
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
* ゲームモード(`game-mode`)と時刻(`time`)のペアを配列で記載します。
  * ゲームモードには以下の値のうちのいずれか1つのみ指定できます。
    * `Survival`
    * `Creative`
    * `Spectator`
    * `Adventure`
  * 時刻はクォーテーションで囲ってください。

```yaml
mode-time:
  - game-mode: Survival
    time: '10:00'
  - game-mode: Creative
    time: '11:00'
  - game-mode: Spectator
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

## License

[GPLv3](./LICENSE)
