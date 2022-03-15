package example.argparse;

import io.d2a.eeee.argparse.Flags;
import io.d2a.eeee.argparse.flags.BooleanFlag;
import io.d2a.eeee.argparse.flags.DoubleFlag;
import io.d2a.eeee.argparse.flags.LongFlag;
import io.d2a.eeee.argparse.flags.StringFlag;

public class ArgParseExample {

    public static void main(String[] args) {
        final StringFlag stringFlag = new StringFlag("string");
        final BooleanFlag booleanFlag = new BooleanFlag("boolean");
        final DoubleFlag doubleFlag = new DoubleFlag("double");
        final LongFlag longFlag = new LongFlag("long");
        Flags.parse(args);

        System.out.println(stringFlag.isUpdated() +  " String: " + stringFlag.get("string-def"));
        System.out.println(booleanFlag.isUpdated() +  " Boolean: " + booleanFlag.get(true));
        System.out.println(doubleFlag.isUpdated() +  " Double: " + doubleFlag.get(13.37));
        System.out.println(longFlag.isUpdated() +  " Long: " + longFlag.get(420L));
    }

}
