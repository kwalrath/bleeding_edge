#library('DOMConstructorsTest');
#import('../../pkg/unittest/unittest.dart');
#import('../../pkg/unittest/html_config.dart');
#import('dart:html');

main() {
  useHtmlConfiguration();
  test('FileReader', () {
    FileReader fileReader = new FileReader();
    expect(fileReader.readyState, equals(FileReader.EMPTY));
  });
}
