// Copyright (c) 2011, the Dart project authors.  Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

// WARNING: Do not edit - generated code.

class _WebGLContextAttributesWrappingImplementation extends DOMWrapperBase implements WebGLContextAttributes {
  _WebGLContextAttributesWrappingImplementation() : super() {}

  static create__WebGLContextAttributesWrappingImplementation() native {
    return new _WebGLContextAttributesWrappingImplementation();
  }

  bool get alpha() { return _get_alpha(this); }
  static bool _get_alpha(var _this) native;

  void set alpha(bool value) { _set_alpha(this, value); }
  static void _set_alpha(var _this, bool value) native;

  bool get antialias() { return _get_antialias(this); }
  static bool _get_antialias(var _this) native;

  void set antialias(bool value) { _set_antialias(this, value); }
  static void _set_antialias(var _this, bool value) native;

  bool get depth() { return _get_depth(this); }
  static bool _get_depth(var _this) native;

  void set depth(bool value) { _set_depth(this, value); }
  static void _set_depth(var _this, bool value) native;

  bool get premultipliedAlpha() { return _get_premultipliedAlpha(this); }
  static bool _get_premultipliedAlpha(var _this) native;

  void set premultipliedAlpha(bool value) { _set_premultipliedAlpha(this, value); }
  static void _set_premultipliedAlpha(var _this, bool value) native;

  bool get preserveDrawingBuffer() { return _get_preserveDrawingBuffer(this); }
  static bool _get_preserveDrawingBuffer(var _this) native;

  void set preserveDrawingBuffer(bool value) { _set_preserveDrawingBuffer(this, value); }
  static void _set_preserveDrawingBuffer(var _this, bool value) native;

  bool get stencil() { return _get_stencil(this); }
  static bool _get_stencil(var _this) native;

  void set stencil(bool value) { _set_stencil(this, value); }
  static void _set_stencil(var _this, bool value) native;

  String get typeName() { return "WebGLContextAttributes"; }
}
