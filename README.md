# TimeToGo

指定した時刻以降にログインしているプレイヤーのゲームモードを、同時に指定したものに変更するプラグインです。

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
