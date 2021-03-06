/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.rosjava.rosjava_extras.hokuyo.scip20;

import com.google.common.base.Preconditions;

/**
 * @author damonkohler@google.com (Damon Kohler)
 */
class Decoder {

  public static int decodeValue(String buffer) {
    int blockSize = buffer.length();
    Preconditions.checkState(2 <= blockSize && blockSize <= 4);
    int result = 0;
    for (int i = 0; i < blockSize; i++) {
      result |= (buffer.charAt(blockSize - i - 1) - 0x30) << i * 6;
    }
    return result;
  }

  public static int[] decodeValues(String buffer, int blockSize) {
    Preconditions.checkArgument(buffer.length() % blockSize == 0);
    int[] data = new int[buffer.length() / blockSize];
    for (int i = 0; i < data.length; i++) {
      int bufferIndex = i * blockSize;
      data[i] = decodeValue(buffer.substring(bufferIndex, bufferIndex + blockSize));
    }
    return data;
  }
}
