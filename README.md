# Delta Schema Compatibility Tester

This repository contains a utility implemented in Scala that validates the compatibility of message contracts as applied to Delta Lake restrictions. 
Current implementation supports protobuf and DDL schema definitions.
The utility uses Apache Spark protobuf, Apache Spark SQL, Delta IO (Delta Lake), and the `lihaoyi` `mainargs` library for command line argument parsing.

## Installation

To use this utility, you need to have the following prerequisites installed:

- Java 17 or higher
- Scala 2.13 or higher

Once you have installed the prerequisites, follow these steps to use the utility:

1. Clone this repository:

```shell
git clone https://github.com/amichel/delta-schema-compatibility-tester.git
```

2. Build the project using SBT:

```shell
cd delta-schema-compatibility-tester
sbt compile
sbt package
```
3. Generate Protobuf Descriptors:
```shell
protoc --java_out=protoc_out mycontract.proto --descriptor_set_out=descriptors/source_schema.descriptor
```
4. Run the utility:

```shell
java -jar target/scala-2.13/delta-schema-compatibility-tester_2.13-0.1.0.jar \
    --schemaFormat <format> \
    --source <source_schema> \
    --target <target_schema> \
    --messageType <message_type> \
    --outType <output_type>
```

Replace `<format>` with the format of the source/target schema validator (e.g., `proto`). Replace `<source_schema>` with the path to the source schema file, `<target_schema>` with the path to the target schema file, `<message_type>` with the message/schema type name in the source/target, and `<output_type>` with the output formatter type (`console`, `tree`, or `ddl`).

For example:

```shell
java -jar target/scala-2.13/delta-schema-compatibility-tester-assembly-1.0.jar \
    --schemaFormat proto \
    --source /path/to/source_schema.descriptor \
    --target /path/to/target_schema.descriptor \
    --messageType MyMessage \
    --outType console
```

Make sure to provide the correct values for each argument.

## License

License
This project is licensed under the Apache License 2.0. Feel free to modify and distribute it as per the terms of the license.
Make sure to include the appropriate Apache License 2.0 file in your repository and update the license headers of the source code files accordingly.

## Contributing

Contributions are welcome! If you find any issues or want to add new features, please open an issue or submit a pull request.

## Acknowledgments

This utility was inspired by the need to validate the compatibility of protobuf file versions applied to Delta Lake restrictions. We would like to acknowledge the following libraries for making this utility possible:

- [Apache Spark](https://spark.apache.org)
- [Apache Spark SQL](https://spark.apache.org/sql)
- [Delta IO (Delta Lake)](https://delta.io)
- [lihaoyi mainargs](https://github.com/lihaoyi/mainargs)

[![Scala CI](https://github.com/amichel/delta-schema-compatibility-tester/actions/workflows/scala.yml/badge.svg?branch=main)](https://github.com/amichel/delta-schema-compatibility-tester/actions/workflows/scala.yml)