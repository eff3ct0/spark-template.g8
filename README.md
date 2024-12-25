# Spark Template (g8)

A [Giter8](https://www.foundweekends.org/giter8) template for Scala Spark Projects.

## Benefits of Using This Template

This template kickstarts your Spark project with a modified version of the popular "word count" example, now enhanced to
handle stop words. This foundational example can be replaced or expanded as per your project requirements, allowing you
to tailor the Spark components to fit your specific needs.

### Advantages

- **Rapid Initialization**: Quickly bootstrap your Spark application with a ready-to-use example.
- **Customizable Base**: Easily swap out the word count example to accommodate your unique project objectives and
  integrate additional Spark components as needed.
- **Encourages Best Practices**: Begin with a project set up for good coding practices, starting with 100% code
  coverage (albeit with just one sample test). It's expected that coverage will naturally adjust as your project grows.

## Creating a new project from this template

Using sbt (0.13.13+) just do

```bash
sbt new eff3ct0/spark-template.g8
```

## Getting Started with the created project

Follow these steps to set up and run your Spark application:

### Running the Example Spark Job Locally

You can test the example Spark job directly from SBT. Open a terminal in the project directory and execute:

```bash 
sbt "run inputFile.txt outputFile.txt"
```

When prompted, choose `CountingLocalApp` to see the example in action.

### Building the project

Prepare your project for distribution or deployment by building it with SBT:

1. Navigate to the project directory:

```bash
cd <project-directory>
```

2. Run the assembly:

```
sbt assembly
```

This command creates a JAR file in the target/scala-<scala-version>/ directory. For more details on configuring *
*sbt-assembly**, refer to the [sbt-assembly](https://github.com/sbt/sbt-assembly) documentation.

### Submitting the Application to a Spark Cluster

To execute your Spark job on a cluster, use the Spark spark-submit script:

```bash
/path/to/spark-home/bin/spark-submit \
  --class <package-name>.CountingApp \
  --name <spark-app> \
  --master <master url> \
  ./target/scala-<scala-version>/<jar-name> \
  <input file> <output file>
```

Make sure to replace placeholders like <package-name>, <master-url>, <jar-name>, <input-file>, and <output-file> with
actual values relevant to your project and environment.

More information on submitting Spark jobs can be found in
the [Spark documentation](https://spark.apache.org/docs/latest/submitting-applications.html).

## License

This project is available under your choice of the Apache 2.0 or CC0 1.0 license. Choose the one that best suits your
needs:

- [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
- [CC0 1.0 Universal (Public Domain Dedication)](https://creativecommons.org/publicdomain/zero/1.0/)

This template is provided "as-is" without any warranties. Modify and distribute as needed to fit your project
requirements.