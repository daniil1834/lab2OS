package dou.opengl;/*
<<<<<<< HEAD
 * Copyright 2020 Viktor Gubin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import org.lwjgl.opengl.GL32C;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;


/**
 * OpenGL shader object helper
<<<<<<< HEAD
 * 
=======
 *  
>>>>>>> 92a4dc6 (LWJGL examples, for article)
 * @author Viktor Gubin
 * 
 */
public final class Shader {

<<<<<<< HEAD
  /**
   * Supported OpenGL 4.3+ shader types
   */
  public static enum Type {
    /**
     * Shader that is intended to run on the programmable compute processor.
     */
    COMPUTE(GL43.GL_COMPUTE_SHADER),
    /**
     * Shader that is intended to run on the programmable fragment processor
     */
    FRAGMENT(GL_FRAGMENT_SHADER),
    /**
     * Shader that is intended to run on the programmable geometry processor
     */
    GEOMETRY(GL32C.GL_GEOMETRY_SHADER), 
    /**
     * Shader that is intended to run on the programmable tessellation processor in the control stage
     */
    TESS_CONTROL(GL40.GL_TESS_CONTROL_SHADER),
    /**
     * Shader that is intended to run on the programmable tessellation processor in the evaluation stage
     */
    TESS_EVALUATION(GL40.GL_TESS_EVALUATION_SHADER),
    /**
     * Shader that is intended to run on the programmable vertex processor
     */
    VERTEX(GL_VERTEX_SHADER);

    private final int gl;

    private Type(int gl) {
      this.gl = gl;
    }
    
    /**
    * Returns OpenGL enumeration value to pass into OpenGL functions
    * 
    * @return OpenGL enumeration value
    */
    public int glEnum() {
      return this.gl;
    }

  }

  private static String loadSource(InputStream source) {
    try (
        Reader reader = new BufferedReader(new InputStreamReader(source, StandardCharsets.UTF_8))) {
      StringBuilder result = new StringBuilder();
      char[] buff = new char[512]; // 1k
      for (int read = reader.read(buff); read > 0; read = reader.read(buff)) {
        result.append(buff, 0, read);
      }
      return result.toString();
    } catch (IOException exc) {
      throw new IllegalStateException("Can not load shader source", exc);
    }
  }

  /**
   * Loads GLSL shader source code from a stream
   * 
   * @param source a stream to load shader from, will be closed
   * @param type shader type to load
   * @return new shader object
   * @throws IllegalStateException when shader can not be read, or compiled
   */
  public static Shader load(InputStream source, Type type) {
    return load(loadSource(source), type);
  }

  /**
   * Loads GLSL shader source code from a string
   * 
   * @param source to load shader from
   * @param type shader type to load
   * @return new shader object
   * @throws IllegalStateException when shader can not be read, or compiled
   */
  public static Shader load(String source, Type type) {
    int id = glCreateShader(type.glEnum());
    glShaderSource(id, source);
    glCompileShader(id);
    int[] errc = {GL_TRUE};
    glGetShaderiv(id, GL_COMPILE_STATUS, errc);
    if (GL_FALSE == errc[0]) {
      throw new IllegalStateException(
          String.format("Error creating %s shader: %s", type.name(), getLogInfo(id)));
    }
    return new Shader(id, type);
  }

  private static String getLogInfo(int id) {
    return glGetShaderInfoLog(id, 1024);
  }

  private final int id;
  private final Type type;

  private Shader(int id, Type type) {
    this.id = id;
    this.type = type;
  }

  /**
   * Returns this shader object type
   * 
   * @return
   */
  public Type getType() {
    return type;
  }

  int getId() {
    return id;
  }

  void delete() {
    glDeleteShader(id);
  }
=======
	/**
	 * Supported OpenGL 4.3+ shader types 
	 */
	public static enum Type {

		COMPUTE(GL43.GL_COMPUTE_SHADER), 
		FRAGMENT(GL_FRAGMENT_SHADER), 
		GEOMETRY(GL32C.GL_GEOMETRY_SHADER),
		TESS_CONTROL(GL40.GL_TESS_CONTROL_SHADER), 
		TESS_EVALUATION(GL40.GL_TESS_EVALUATION_SHADER),
		VERTEX(GL_VERTEX_SHADER);

		private final int gl;

		private Type(int gl) {
			this.gl = gl;
		}

		public int glEnum() {
			return this.gl;
		}

	}

	private static String loadSource(InputStream source) {
		try (Reader reader = new BufferedReader(new InputStreamReader(source, StandardCharsets.UTF_8))) {
			StringBuilder result = new StringBuilder();
			char[] buff = new char[512]; // 1k
			for (int read = reader.read(buff); read > 0; read = reader.read(buff)) {
				result.append(buff, 0, read);
			}
			return result.toString();
		} catch (IOException exc) {
			throw new IllegalStateException("Can not load shader source", exc);
		}
	}

	/**
	 * Loads GLSL shader source code from a stream
	 * @param source a stream to load shader from, will be closed
	 * @param type shader type to load
	 * @return new shader object
	 * @throws IllegalStateException when shader can not be read, or compiled
	 */
	public static Shader load(InputStream source, Type type) {
		return load(loadSource(source), type);
	}

	private static Shader load(String source, Type type) {
		int id = glCreateShader(type.glEnum());
		glShaderSource(id, source);
		glCompileShader(id);
		int[] errc = { GL_TRUE };
		glGetShaderiv(id, GL_COMPILE_STATUS, errc);
		if (GL_FALSE == errc[0]) {
			throw new IllegalStateException( String.format("Error creating %s shader: %s", type.name() , getLogInfo(id) ) );
		}
		return new Shader(id, type);
	}

	private static String getLogInfo(int id) {
		return glGetShaderInfoLog(id, 1024);
	}

	private final int id;
	private final Type type;

	private Shader(int id, Type type) {
		this.id = id;
		this.type = type;
	}

	/**
	 * Returns this shader object type
	 * @return
	 */
	public Type getType() {
		return type;
	}

	int getId() {
		return id;
	}

	void delete() {
		glDeleteShader(id);
	}
>>>>>>> 92a4dc6 (LWJGL examples, for article)

}
