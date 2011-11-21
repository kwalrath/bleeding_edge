// Copyright (c) 2011, the Dart project authors.  Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

// WARNING: Do not edit - generated code.

class _SVGAnimatedStringWrappingImplementation extends DOMWrapperBase implements SVGAnimatedString {
  _SVGAnimatedStringWrappingImplementation() : super() {}

  static create__SVGAnimatedStringWrappingImplementation() native {
    return new _SVGAnimatedStringWrappingImplementation();
  }

  String get animVal() { return _get_animVal(this); }
  static String _get_animVal(var _this) native;

  String get baseVal() { return _get_baseVal(this); }
  static String _get_baseVal(var _this) native;

  void set baseVal(String value) { _set_baseVal(this, value); }
  static void _set_baseVal(var _this, String value) native;

  String get typeName() { return "SVGAnimatedString"; }
}
