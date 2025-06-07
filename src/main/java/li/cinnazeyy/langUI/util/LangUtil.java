package li.cinnazeyy.langUI.util;

import li.cinnazeyy.langUI.LangUI;
import li.cinnazeyy.langlibs.core.LangLibAPI;

import li.cinnazeyy.langlibs.core.language.Language;
import li.cinnazeyy.langlibs.core.file.LanguageFile;
import li.cinnazeyy.langlibs.core.language.LanguageUtil;
import org.bukkit.command.CommandSender;

public class LangUtil extends LanguageUtil {
    private static LangUtil langUtilInstance;

    public static void init() {
        if (langUtilInstance != null) return;
        LangLibAPI.register(LangUI.getPlugin(), new LanguageFile[]{
                new LanguageFile(LangUI.getPlugin(), 1.0, Language.en_GB),
                new LanguageFile(LangUI.getPlugin(), 1.0, Language.de_DE, "de_AT", "de_CH"),
                new LanguageFile(LangUI.getPlugin(), 1.0, Language.fr_FR)
        });
        langUtilInstance = new LangUtil();
    }

    public LangUtil() {
        super(LangUI.getPlugin());
    }

    public static LangUtil getInstance() {
        return langUtilInstance;
    }

    @Override
    public String get(CommandSender sender, String key) {
        return super.get(sender, key);
    }
}
