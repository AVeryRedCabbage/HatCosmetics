package me.Tonus_.hatCosmetics.utility;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


public class MessageHelper {
    private final Logger logger;

    public MessageHelper(@NotNull Plugin plugin) {
        this.logger = plugin.getLogger();
    }

    /**
     * Formats a message for color by replacing all '&' with '§'
     *
     * @param msg Message to format
     * @return Formatted message
     */
    public String parseColorCodes(@NotNull String msg) {
        return msg.replaceAll("&([1-9a-eA-EKkLlMmNnOoRr])", "§$1");
    }

    // TODO: Look to generalise format method better
    // TODO: Potentially look if this can be done in place or more efficintly
    public String format(@NotNull String format, String formatArg) {
        StringBuilder result = new StringBuilder(format.length());
        int start = 0;
        int openBrace = format.indexOf('{', start);

        while (openBrace != -1) {
            int closeBrace = format.indexOf('}', openBrace);
            if (closeBrace == -1) {
                break;
            }

            result.append(format, start, openBrace);
            result.append(formatArg);
            start = closeBrace + 1;
            openBrace = format.indexOf('{', start);
        }

        result.append(format, start, format.length());

        return result.toString();
    }

    // TODO: Potentially look if this can be done in place or more efficintly
    // TODO: Add JavaDoc
    public String format(String format, Map<String, String> formatArgs) {
        StringBuilder result = new StringBuilder(format.length());
        StringBuilder placeholder = new StringBuilder();
        boolean inPlaceholder = false;

        for (int i = 0; i < format.length(); i++) {
            char c = format.charAt(i);

            if (c == '{') {
                inPlaceholder = true;
                placeholder.setLength(0);
            } else if (c == '}' && inPlaceholder) {
                inPlaceholder = false;
                String key = placeholder.toString();
                if (formatArgs.containsKey(key)) {
                    result.append(formatArgs.get(key));
                } else {
                    logger.log(Level.WARNING, "Placeholder \"" + placeholder + "\" not defined in the template: " + format);
                    result.append('{').append(key).append('}');
                }
            } else if (inPlaceholder) {
                placeholder.append(c);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
