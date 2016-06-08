package freemarker.cache;

import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import freemarker.cache.TemplateLoader;
import freemarker.template.utility.StringUtil;

public class StringTemplateLoader implements TemplateLoader {

	private final ConcurrentHashMap<String, StringTemplateSource> templates = new ConcurrentHashMap<String, StringTemplateSource>();

	public void putTemplate(String name, String templateSource) {
		putTemplate(name, templateSource, System.currentTimeMillis());
	}

	public void putTemplate(String name, String templateSource, long lastModified) {
		templates.put(name, new StringTemplateSource(name, templateSource, lastModified));
	}

	public void deleteTemplate(String name) {
		templates.remove(name);
	}

	public void closeTemplateSource(Object templateSource) {
	}

	public void clearTemplateSource() {
		templates.clear();
	}

	public Object findTemplateSource(String name) {
		return templates.get(name);
	}

	public long getLastModified(Object templateSource) {
		return ((StringTemplateSource) templateSource).lastModified;
	}

	public Reader getReader(Object templateSource, String encoding) {
		return new StringReader(((StringTemplateSource) templateSource).source);
	}

	private static class StringTemplateSource {
		private final String name;
		private final String source;
		private final long lastModified;

		StringTemplateSource(String name, String source, long lastModified) {
			if (name == null) {
				throw new IllegalArgumentException("name == null");
			}
			if (source == null) {
				throw new IllegalArgumentException("source == null");
			}
			if (lastModified < -1L) {
				throw new IllegalArgumentException("lastModified < -1L");
			}
			this.name = name;
			this.source = source;
			this.lastModified = lastModified;
		}

		public boolean equals(Object obj) {
			if (obj instanceof StringTemplateSource) {
				return name.equals(((StringTemplateSource) obj).name);
			}
			return false;
		}

		public int hashCode() {
			return name.hashCode();
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(TemplateLoaderUtils.getClassNameForToString(this));
		sb.append("(Map { ");
		int cnt = 0;
		for (Iterator<?> it = templates.keySet().iterator(); it.hasNext();) {
			cnt++;
			if (cnt != 1) {
				sb.append(", ");
			}
			if (cnt > 10) {
				sb.append("...");
				break;
			}
			sb.append(StringUtil.jQuote(it.next()));
			sb.append("=...");
		}
		if (cnt != 0) {
			sb.append(' ');
		}
		sb.append("})");
		return sb.toString();
	}

}
