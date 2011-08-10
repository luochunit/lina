package parser;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 10:17 PM
 */
public class PackageParser implements Parser {
    final Package _package;

    public PackageParser(Package _package) {
        this._package = _package;
    }

    @Override
    public void parse(StringBuffer buffer) {
        buffer.append("package ").append(_package.getName()).append(";\n");
    }
}
