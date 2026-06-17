import httpx

SPRING_URL = "http://localhost:8081"


async def forward_get(endpoint, headers=None):
    async with httpx.AsyncClient() as client:
        response = await client.get(
            f"{SPRING_URL}{endpoint}",
            headers=headers
        )
        return response.json()


async def forward_post(endpoint, payload, headers=None):
    async with httpx.AsyncClient() as client:
        response = await client.post(
            f"{SPRING_URL}{endpoint}",
            json=payload,
            headers=headers
        )
        return response.json()


async def forward_delete(endpoint, headers=None):
    async with httpx.AsyncClient() as client:
        response = await client.delete(
            f"{SPRING_URL}{endpoint}",
            headers=headers
        )
        return response.json()