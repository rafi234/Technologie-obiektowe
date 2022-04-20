package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;

public class Mkdir extends Command {
    public Mkdir (IContext ctx, ICommand next) {
        super("mkdir", ctx, next, null, "Użycie mkdir <sciezka>");
    }

}
