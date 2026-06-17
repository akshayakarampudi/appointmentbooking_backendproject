from fastapi import Header

async def get_headers(
    authorization: str = Header(None)
):
    return {
        "Authorization": authorization
    }