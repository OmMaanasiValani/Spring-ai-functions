Project demonstartes concept of function callback by retrieving real time stock information of the company asked by the user.
Implementation summary:
1) User requests by proving company name (user prompt)
2) Open AI is fed with prompt to provide brief about the company (system prompt)
3) Open AI LLM then invokes a call back function which is external API via Ninja API that provides with real time share information.
4) LLM combines the brief about company and real time stock information and provides comprehensive response. 
