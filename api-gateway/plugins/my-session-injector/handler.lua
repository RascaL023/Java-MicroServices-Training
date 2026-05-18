local redis = require("resty.redis")
local cjson = require("cjson.safe")

local MySessionInjector = {
    PRIORITY = 1000,
    VERSION = "1.0.0",
}

function MySessionInjector:access(conf)
    local auth_header = kong.request.get_header("authorization")
    if not auth_header then
        return kong.response.exit(
            401, {
                status = 401,
                errorType = "Unauthorized",
                message = "Harap login terlebih dahulu"
            }
        )
    end

    local token = auth_header:match("^[Bb]earer%s+(.+)$")
    if not token then
        return kong.response.exit(401, { message = "Format token tidak valid" })
    end
    
end
