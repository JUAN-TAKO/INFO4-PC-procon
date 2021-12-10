all:
	javac prodcons/*.java prodcons/Tests/*.java prodcons/v1/*.java prodcons/v2/*.java prodcons/v3/*.java prodcons/v5/*.java prodcons/v6/*.java prodcons/v7/*.java -d bin
	java -cp bin prodcons.Tests.Test