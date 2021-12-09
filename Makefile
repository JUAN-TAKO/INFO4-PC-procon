all:
	javac prodcons/*.java prodcons/Tests/*.java prodcons/v6/*.java -d bin
	java -cp bin prodcons.Tests.Test