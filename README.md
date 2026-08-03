# Sticky

### A simple note-taking application akin to notepad with a drawing mode.

## Usage

```bash
cd sticky
chmod +x run.sh
bash run.sh
```

You may also want to alias this in your `.bashrc` or PowerShell profile depending on your CLI environment for quick-access.

```bash
# .bashrc
alias sticky="bash /home/user/sticky/run.sh"
```

## Controls

- Type normally — plain text, indentation preserved on paste
- `Ctrl+D` — toggle drawing mode (black pen, freehand)

## Config

Settings live in `application.properties`:

| Key | Description |
|---|---|
| `window.title` | Window title |
| `window.width` / `window.height` | Window size in pixels |
| `font.family` | Font name (e.g. `Monospaced`, `Consolas`) |
| `font.size` | Font size |
| `window.tab-size` | Spaces per tab |
| `line.is-wrapped` | Wrap long lines (`true`/`false`) |

## Requirements

- Java 25+