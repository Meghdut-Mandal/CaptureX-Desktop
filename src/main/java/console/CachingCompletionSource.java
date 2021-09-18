package console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CachingCompletionSource implements CompletionSource
{
	private final Map<String, List<String>> completionCache = new HashMap<>();
	
	public List<String> complete(String text)
	{
		if(completionCache.containsKey(text))
		{
			return completionCache.get(text);
		}
		else
		{
			List<String> results = doCompletion(text);
			completionCache.put(text, results);
			return results;
		}
	}
	
	protected abstract List<String> doCompletion(String input);
}
