// TARGET_BACKEND: JVM_IR
// WITH_RUNTIME

// FILE: ProjectFile.java

public class ProjectFile {

}

// FILE: Dictionary.java

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Dictionary {
    public static final Dictionary EMPTY_DICTIONARY = new Dictionary();

    @Nullable
    public String getString(@NotNull String key) {
        return getString(key, null);
    }

    public String getString(@NotNull String key, @Nullable String defValue) {
        if (key.equals("repositoryURL")) {
            return "my/your/OK";
        }
        return defValue;
    }

    public Dictionary getDictionary(@NotNull String key, @Nullable Dictionary defValue) {
        return defValue;
    }
}

// FILE: MyObject.java

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyObject extends Dictionary {
    @NotNull private final ProjectFile myFile;

    public MyObject (@NotNull final ProjectFile file) {
        myFile = file;
    }

    @Nullable
    public String getComment() {
        String result = getString("name", null);
        if (result == null) result = getString("isa", null);
        return result;
    }
}

// FILE: ContainerItem.java

public abstract class ContainerItem extends MyObject {
    public ContainerItem (ProjectFile file) {
        super(file);
    }
}


// FILE: Reference.java

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Reference extends ContainerItem {
    public Reference(ProjectFile file) {
        super(file);
    }

    @Override
    public String getComment() {
        String name = getName();
        if (name != null) return name;
        name = getPath();
        if (name != null) return name;
        return null;
    }

    @Nullable
    public String getName() {
        return getString("name");
    }

    @Nullable
    public String getPath() {
        String path = getString("path");
        return path;
    }
}

// FILE: RemoteSwiftPackageReference.kt

class RemoteSwiftPackageReference(projectFile: ProjectFile) : Reference(projectFile) {
    val repositoryURL: String?
        get() = getString("repositoryURL")

    val requirement: Dictionary
        get() = getDictionary("requirement", Dictionary.EMPTY_DICTIONARY)

    override fun getComment(): String? {
        val comment = repositoryURL?.split("/")?.lastOrNull()?.let { name ->
            name
        }
        return comment ?: super.getComment()
    }
}

fun box(): String {
    val projectFile = ProjectFile()
    val reference = RemoteSwiftPackageReference(projectFile)
    return reference.getComment()!!
}